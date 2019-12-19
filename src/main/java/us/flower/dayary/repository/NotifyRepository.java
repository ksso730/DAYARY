package us.flower.dayary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Noti;

public interface NotifyRepository extends JpaRepository<Noti, Long>{

	List<Noti> findByPeople_id(long peopleId);

	long countByPeople_id(long peopleId);

}
