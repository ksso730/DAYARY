package us.flower.dayary.repository.moim.meetup;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Meetup;

public interface MoimMeetUpRepository extends JpaRepository<Meetup, Long>{

}
