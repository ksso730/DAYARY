package us.flower.dayary.service.moim.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.common.FileManager;
import us.flower.dayary.repository.moim.picture.MoimBoardFileRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;


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
	
	@Override
	public boolean saveFile(MultipartFile[] file) {
		// TODO Auto-generated method stub
		boolean check =true;
		int size = file.length;
		
		try {
			for(int i=0; i<size; i++)
			{
				//String fileName = file[i].getName();
				String fileName = file[i].getOriginalFilename();
				//fileManager.fileUpload(file[i], moimImagePath+"/"+fileName+".jpg");
				fileManager.fileUpload(file[i], moimImagePath+"/"+fileName);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("오류가 났어용");
			check = false;
		}
		return check;
	}
	
}
