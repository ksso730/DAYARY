package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.CommonId;

public interface CommonRepository extends JpaRepository<Common, CommonId>{

}
