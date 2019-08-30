package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long>{

    boolean existsById(String id);

    People findById(String id);

}
