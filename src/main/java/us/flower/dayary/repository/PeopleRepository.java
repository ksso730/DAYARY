package us.flower.dayary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long>{

    boolean existsById(String id);

    People findById(String id);

	Object findByIdOrName(String idOrname, String idOrname2);

	People findByNo(Long no);

    

}
