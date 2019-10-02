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
	//@Query("select count(a.id) as id from People a inner join MoimPeople b on a.id=b.people inner join Moim c on b.moim=c.id where b.joinrole='study' and a.id=(:people_no) and c.id=(:no)")
	//List<MoimPeople> selectMoim_idAndPeople_id(long no, long peopleId);

	void deleteByPeopleAndMoim(People people, Moim moim);

	boolean existsByMoim_idAndPeople_id(long no,long peopleId); 
}
