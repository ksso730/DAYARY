package us.flower.dayary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{

	 
}
