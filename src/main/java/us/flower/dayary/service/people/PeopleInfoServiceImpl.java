package us.flower.dayary.service.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import us.flower.dayary.common.FileManager;

@Service
public class PeopleInfoServiceImpl implements PeopleInfoService{
	
	@Value("${moimImagePath}")
	private String moimImagePath;
	@Autowired
	private FileManager fileManager;
	@Override
	public byte[] getMoimImage(String imageName) throws Exception {
		return fileManager.getByteArray(moimImagePath+"/"+imageName);
	}

}
