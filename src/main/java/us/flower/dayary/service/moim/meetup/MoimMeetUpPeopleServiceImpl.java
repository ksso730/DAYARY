package us.flower.dayary.service.moim.meetup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.MeetupPeople;
import us.flower.dayary.repository.moim.meetup.MeetupPeopleRepository;

@Service
public class MoimMeetUpPeopleServiceImpl implements MoimMeetUpPeopleService{

	@Autowired
	MeetupPeopleRepository meetuppeopleRepository;
	
	
	@Override
	public List<MeetupPeople> selectmeetupIdAndpeopleId(long meetupListId, long peopleId) {
		return meetuppeopleRepository.findByMeetup_idAndPeople_id(meetupListId,peopleId);
	}

}
