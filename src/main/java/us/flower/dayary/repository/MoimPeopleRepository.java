package us.flower.dayary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{


	@Query("select p.id,p.name from MoimPeople mp inner join Moim m on mp.moim=m.no inner join People p on mp.people=p.no where m.no = ?1")
	public List<MoimPeople> findByjoinmoimId(Long no);
}
