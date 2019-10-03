package us.flower.dayary.service.moim.joinpeople;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;

public interface MoimJoinPeopleService {

	void deleteMoimPeople(long moimPeopleNo);//모임탈퇴(자기자신)

	void banMoimpeople(People people, Moim moim);

}
