package us.flower.dayary.service.moim.meetup;

import javax.validation.Valid;

import us.flower.dayary.domain.Meetup;

public interface MoimMeetUpService {

	void moimoffMake(@Valid Meetup meetUp, long peopleId, long no);

}
