package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
