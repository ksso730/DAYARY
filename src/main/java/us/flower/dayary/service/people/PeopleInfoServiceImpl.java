package us.flower.dayary.service.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import us.flower.dayary.common.FileManager;
import us.flower.dayary.repository.people.PeopleRepository;

@Service
public class PeopleInfoServiceImpl implements PeopleInfoService{
	
	@Autowired
	PeopleRepository peopleRepository;

	@Value("${moimImagePath}")
	private String moimImagePath;
	@Autowired
	private FileManager fileManager;
	@Override
	public byte[] getMoimImage(String imageName) throws Exception {
		return fileManager.getByteArray(moimImagePath+"/"+imageName);
	}
	@Override
	public void deletePeople(long peopleId) {
		peopleRepository.deleteById(peopleId);
	}

}
