package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.CommonId;

import java.util.List;

public interface CommonRepository extends JpaRepository<Common, CommonId>{
	List<Common> findByCommHead(String commonHead);

	Common findBycommName(String subject);

}