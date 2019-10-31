package us.flower.dayary.service.moim.image;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.common.FileManager;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardFileRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;


@Service
@Transactional
public class MoimImageImpl implements MoimImage{
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
	
	
	@Override
	public String[] saveFile(MoimBoard moid_moard,MultipartFile[] file) {
		// TODO Auto-generated method stub
		String[] result = new String[file.length];
		int size = file.length;
		
		try {
			for(int i=0; i<size; i++)
			{
				MoimBoardFile mbFile = new MoimBoardFile();
				//String fileName = file[i].getName();
				String fileName = file[i].getOriginalFilename();
				result[i] = fileName;
								
				//fileManager.fileUpload(file[i], moimImagePath+"/"+fileName+".jpg");
				fileManager.fileUpload(file[i], moimImagePath+"/"+fileName);
				
				mbFile.setMoimBoard(moid_moard);
				mbFile.setReal_name(fileName);
				mbFile.setFile_name("");
				mbFile.setFile_size("");
				mbFile.setFile_locate(moimImagePath+"/"+fileName);
				
				mbFileRepository.save(mbFile);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("오류가 났어용");
		}
		return result;
	}
	@Override
	public boolean writePost(long peopleId,long boardId,long moimId, String title,MultipartFile[] file) {
		boolean result = true;
		String[] fileName;
		
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
			mbRepository.save(moimBoard);
			
			fileName = saveFile(moimBoard,file);
		}catch(Exception e) {
			
		}
		
		return result;
	}
	
}
