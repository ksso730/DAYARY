package us.flower.dayary.repository.moim;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{

	@Query("select count(id) as countId from MoimPeople where moim=(:no)")
	long countAllMoimPeople(@Param("no") long no);

	List<MoimPeople> findByMoim_idAndPeople_id(long no, long peopleId);

	void deleteByPeopleAndMoim(People people, Moim moim);

	List<MoimPeople> findByPeople_id(long peopleId);
	
	boolean existsByMoim_idAndPeople_id(long no,long peopleId);

	List<MoimPeople> findByMoim_id(long no);
	
	@Modifying
	@Transactional
	@Query("UPDATE MoimPeople SET joinCondition='Y' WHERE moim=(:moim) and people=(:people)")
	void updateMoimPeoplejoinCondition(@Param("people") People people,@Param("moim") Moim moim);


}
 