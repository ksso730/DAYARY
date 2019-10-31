package us.flower.dayary.repository.moim.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.MoimPeople;

public interface MoimBoardFileRepository extends JpaRepository<MoimBoardFile, Long>{
	@Query("SELECT count(a.id) FROM Moim a INNER JOIN MoimBoard b ON a.id=b.moim INNER JOIN MoimBoardFile c ON b.id=c.moid_moard WHERE a.id=(:no)")
	long picturecount(@Param("no") long no);

}
