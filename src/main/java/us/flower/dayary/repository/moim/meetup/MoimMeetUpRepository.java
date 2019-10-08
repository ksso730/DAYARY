package us.flower.dayary.repository.moim.meetup;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Meetup;

public interface MoimMeetUpRepository extends JpaRepository<Meetup, Long>{

	List<Meetup> findByMoim_id(long no, Sort sort);

}
