package us.flower.dayary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long>{



	People findByEmail(String email);

	boolean existsByEmail(String email);
	@Query("select a.email as email from People a inner join MoimPeople b on a.id=b.people where b.joinrole='study' and a.id=(:people_no)")
	Optional<People> findPeopleOne(@Param("people_no") long people_no);


    

}
