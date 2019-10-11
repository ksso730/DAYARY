package us.flower.dayary.repository.moim.meetup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.MeetupPeople;

public interface MeetupPeopleRepository extends JpaRepository<MeetupPeople,Long>{

	List<MeetupPeople> findByMeetup_idAndPeople_id(long l, long peopleId);

	List<MeetupPeople> findByMeetup_id(long no1);

}
