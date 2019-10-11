package us.flower.dayary.repository.moim.meetup;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.Meetup;

public interface MoimMeetUpRepository extends JpaRepository<Meetup, Long>{

	List<Meetup> findByMoim_id(long no, Pageable pageable);

	
	@Query("select count(id) as countId from MoimPeople where moim=(:no)")
	long countAllMoimPeople(@Param("no") long no);


	List<Meetup> findByMoim_id(long no, Sort sort);
 
}
