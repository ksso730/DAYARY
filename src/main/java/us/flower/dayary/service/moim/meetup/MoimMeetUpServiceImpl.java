package us.flower.dayary.service.moim.meetup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.moim.meetup.MoimMeetUpRepository;

@Service
public class MoimMeetUpServiceImpl implements MoimMeetUpService{

	@Autowired
	MoimMeetUpRepository meetUpRepository;
	
	
	@Override
	public void moimoffMake(@Valid Meetup meetUp, long peopleId, long no) {
		People people=new People();
		people.setId(peopleId);
		Moim moim=new Moim();
		moim.setId(no);
		
		meetUp.setPeople(people);
		meetUp.setMoim(moim);
		meetUp.setIntro(meetUp.getIntro());
		meetUp.setMeetDate(meetUp.getMeetDate());
		meetUp.setPeopleLimit(meetUp.getPeopleLimit());
		meetUp.setDetailAddress(meetUp.getDetailAddress());
		meetUp.setLocationX(meetUp.getLocationX());
		meetUp.setLocationY(meetUp.getLocationY());
		meetUp.setMoney(meetUp.getMoney());
		meetUp.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
		
		meetUpRepository.save(meetUp);
	}


}
