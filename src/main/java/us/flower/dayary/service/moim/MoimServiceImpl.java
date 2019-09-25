package us.flower.dayary.service.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.common.FileManager;
import us.flower.dayary.common.TokenGenerator;
import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.CommonRepository;
import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.people.PeopleRepository;

@Service
@Transactional
public class MoimServiceImpl implements moimService{

	@Value("${moimImagePath}")
	private String moimImagePath;

	@Autowired
    private PeopleRepository peopleRepository;
	@Autowired
    private MoimRepository moimRepository;
	@Autowired
    private CommonRepository commonRepository;
	@Autowired
	private MoimPeopleRepository moimpeopleRepository;
	
	@Autowired
    private TokenGenerator tokenGenerator;
	@Autowired
	private FileManager fileManager;

	public Map<String, Object> getMoimCategory(){
		List<Common> cateList= (List<Common>) commonRepository.findAll();
	
		Map<String, Object> categoryList = new HashMap<String, Object>();
		categoryList.put("_category", cateList);
		
		return categoryList;
	}
	
    public void saveMoim(String email, String subject, Moim moim, MultipartFile file) {

        People people = peopleRepository.findByEmail(email);
        Common category=commonRepository.findBycommName(subject);
        //이미지파일이름생성
        String imageName="";
		while(true){
        	imageName=tokenGenerator.getToken();
			//DB에 파일이름이 존재하지 않으면 moim domain에 set
        	if(!moimRepository.existsByImageName(imageName)){
				moim.setImageName(imageName);
				break; 
			}
		}
  
        //이미지파일확장자추출
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        moim.setImageExtension(fileExtension);

        //파일업로드
        try { 
            fileManager.fileUpload(file, moimImagePath+"/"+imageName+"."+fileExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }

        moim.setPeople(people);
        moim.setCategory(category);
        moim.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        moim.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));

        moimRepository.save(moim);
    }
 

	public Optional<Moim> findMoimone(long no) {
		return moimRepository.findById(no);
	}

    public byte[] getMoimImage(String imageName) throws Exception {
        return fileManager.getByteArray(moimImagePath+"/"+imageName);
    }

	public MoimPeople moimParticipant(long peopleId, long moimId) {
		Moim moim=new Moim();
		moim.setId(moimId);
		
		People people=new People();
		people.setId(peopleId);
		
		MoimPeople moimPeople=new MoimPeople();
		moimPeople.setMoim(moim);
		moimPeople.setPeople(people);
		moimPeople.setJoinrole("study"); 
		
		return moimpeopleRepository.save(moimPeople);
	}

	@Override
	@Transactional
	public void deleteMoimOne(long moimNo) {
		moimRepository.deleteById(moimNo);
	}

	@Override
	public String findPeopleOne(Long people_no) {
		return peopleRepository.findPeopleOne(people_no);
	}

	@Override
	public String findMoimPeopleNoOne(long peopleId) {
		
		return peopleRepository.findMoimPeopleNoOne(peopleId);
	}




	


}
