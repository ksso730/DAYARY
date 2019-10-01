package us.flower.dayary.service.moim.joinpeople;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.moim.MoimPeopleRepository;

@Service
public class MoimJoinPeopleServiceImpl implements MoimJoinPeopleService{

	@Autowired
	MoimPeopleRepository moimpeopleRepository;
	
	@Override
	public void deleteMoimPeople(long moimPeopleNo) {
		moimpeopleRepository.deleteById(moimPeopleNo);
	}
	
	@Transactional
	@Override
	public void banMoimpeople(People people, Moim moim) {
		 moimpeopleRepository.deleteByPeopleAndMoim(people,moim);
	}

}
