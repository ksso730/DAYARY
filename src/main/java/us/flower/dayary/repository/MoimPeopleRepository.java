package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.MoimPeopleId;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{

}
