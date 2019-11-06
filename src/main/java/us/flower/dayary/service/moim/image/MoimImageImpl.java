package us.flower.dayary.service.moim.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import us.flower.dayary.common.FileManager;
import us.flower.dayary.common.TokenGenerator;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.QMoim;
import us.flower.dayary.domain.QMoimBoard;
import us.flower.dayary.domain.QMoimBoardFile;

import static us.flower.dayary.domain.QMoimBoard.moimBoard;
import static us.flower.dayary.domain.QMoimBoardFile.moimBoardFile;
import us.flower.dayary.domain.DTO.MoimBoardImage;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardFileRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;

@Repository
@Transactional
public class MoimImageImpl implements MoimImage {
	
	@Autowired 
	private FileManager fileManager;
	@Value("${moimImagePath}")
	private String moimImagePath; 
	@Autowired
	private MoimBoardFileRepository mbFileRepository;
	@Autowired
	private MoimBoardRepository mbRepository;
	@Autowired
	private BoardGroupRepository bgRepository;
	@Autowired
	private PeopleRepository peopleRepository;
	@Autowired
	private MoimRepository moimRepository;
	@Autowired
    private TokenGenerator tokenGenerator;
	
	

	public Page<MoimBoard> search(final Pageable page,long no) {
    	Page<MoimBoard> result;
    	BoardGroup boardGroup = new BoardGroup();
    	Moim moim = new Moim();
    	
    	moim.setId(no);
    	boardGroup.setId(8);
    	
    	result = mbRepository.searchRepresent(boardGroup,moim,0,page);
    	
		return result;
	}
	
	@Override
	public List<MoimBoardFile> saveFile(MoimBoard moid_moard,MultipartFile[] file) {
		// TODO Auto-generated method stub
		String[] result = new String[file.length];
		int size = file.length;
		List<MoimBoardFile> lists = new ArrayList<MoimBoardFile>();
		try {
			for(int i=0; i<size; i++)
			{
		        String file_name="";
		        MoimBoardFile mbFile = new MoimBoardFile();
				while(true){
		        	file_name=tokenGenerator.getToken();
					//DB에 파일이름이 존재하지 않으면 moim domain에 set
		        	if(!mbFileRepository.existsByFilename(file_name)){
		        		mbFile.setFilename(file_name);
						break; 
					}
				}
				
				//String fileName = file[i].getName();
				String fileName = file[i].getOriginalFilename();
				result[i] = fileName;
								
				//fileManager.fileUpload(file[i], moimImagePath+"/"+fileName+".jpg");
				fileManager.fileUpload(file[i], moimImagePath+"/"+fileName);
				
				mbFile.setMoid_moard(moid_moard);
				mbFile.setReal_name(fileName);

				mbFile.setFile_size("");
				mbFile.setFile_locate(moimImagePath+"/"+fileName);
				mbFile.setRepresentImage(i);
				mbFileRepository.save(mbFile);
				lists.add(mbFile);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("오류가 났어용");
		}
		return lists;
	}
	@Override
	public boolean writePost(long peopleId,long boardId,long moimId, String title,MultipartFile[] file) {
		boolean result = true;
		String[] fileName;
		List<MoimBoardFile> lists = new ArrayList<MoimBoardFile>();
		try {
			/*
				CrudRepository 상속시 findById만 쓰면 Optional<> 형식으로 반환을 해주기 때문에
				해결방안
				1. get()메소드를 이용하여 값을 가져온다.
				2. JpaRepository 상속으로 바꾼다.
			*/
			People people = peopleRepository.findById(peopleId).get();
			BoardGroup boardGroup = bgRepository.findById(boardId).get();
			Moim moim = moimRepository.findById(moimId).get();
			char delete_flag = 'N';
			
			MoimBoard moimBoard = new MoimBoard();
			moimBoard.setPeople(people);
			moimBoard.setBoardGroup(boardGroup);
			moimBoard.setMoim(moim);
			moimBoard.setTitle(title);
			moimBoard.setMemo("");
			moimBoard.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
			moimBoard.setUpdate_date(new java.sql.Date(System.currentTimeMillis()));
			moimBoard.setDelete_flag(delete_flag);
			moimBoard.setHeart(0L);
			lists = saveFile(moimBoard,file);
			
			moimBoard.setMoimboardfile(lists);
			mbRepository.save(moimBoard);
			
			
		}catch(Exception e) {
			
		}
		
		return result;
	}
	
}
