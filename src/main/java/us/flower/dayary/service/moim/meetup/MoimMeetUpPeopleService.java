package us.flower.dayary.service.moim.meetup;

import java.util.List;

import us.flower.dayary.domain.MeetupPeople;

public interface MoimMeetUpPeopleService {

	List<MeetupPeople> selectmeetupIdAndpeopleId(long meetupListId, long peopleId);

}
