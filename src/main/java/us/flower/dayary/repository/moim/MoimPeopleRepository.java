package us.flower.dayary.repository.moim;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{

	@Query("select count(id) as countId from MoimPeople where moim=(:no)")
	long countAllMoimPeople(@Param("no") long no);

	List<MoimPeople> findByMoim_idAndPeople_id(long no, long peopleId);

	void deleteByPeopleAndMoim(People people, Moim moim);

	List<MoimPeople> findByPeople_id(long peopleId);

  
	 
}
