package us.flower.dayary.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.common.FileManager;
import us.flower.dayary.common.TokenGenerator;
import us.flower.dayary.domain.Category;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.CategoryRepository;
import us.flower.dayary.repository.MoimPeopleRepository;
import us.flower.dayary.repository.MoimRepository;
import us.flower.dayary.repository.PeopleRepository;

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
    private CategoryRepository categoryRepository;
	@Autowired
	private MoimPeopleRepository moimpeopleRepository;
	
	@Autowired
    private TokenGenerator tokenGenerator;
	@Autowired
	private FileManager fileManager;

	public Map<String, Object> getMoimCategory(){
		List<Category> cateList= (List<Category>) categoryRepository.findAll();
	
		Map<String, Object> categoryList = new HashMap<String, Object>();
		categoryList.put("_category", cateList);
		
		return categoryList;
	}
	
    public void saveMoim(String email, String subject, Moim moim, MultipartFile file) {

        People people = peopleRepository.findByEmail(email);
        Category category = categoryRepository.findBySubject(subject);

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

        moim.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        moim.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        moim.setPeople(people);
        moim.setCategory(category);

        moimRepository.save(moim);
    }
 

	public Optional<Moim> findMoimone(long no) {
		return moimRepository.findById(no);
	}

    public byte[] getMoimImage(String imageName) throws Exception {
        return fileManager.getByteArray(moimImagePath+"/"+imageName);
    }

	public MoimPeople moimParticipant(long peopleId, long moimNo) {
		Moim moim=new Moim();
		moim.setNo(moimNo);
		
		People people=new People();
		people.setId(peopleId);
		
		MoimPeople moimPeople=new MoimPeople();
		moimPeople.setMoim(moim);
		moimPeople.setPeople(people);
		
		return moimpeopleRepository.save(moimPeople);
	}



	


}
