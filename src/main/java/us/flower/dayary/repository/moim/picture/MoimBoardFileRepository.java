package us.flower.dayary.repository.moim.picture;


import javax.annotation.Nullable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.MoimBoardFile;
 
public interface MoimBoardFileRepository extends JpaRepository<MoimBoardFile, Long>{
	@Nullable
	MoimBoardFile findByMoimBoard_id(long id);
	@Query("SELECT count(a.id) FROM Moim a INNER JOIN MoimBoard b ON a.id=b.moim INNER JOIN MoimBoardFile c ON b.id=c.moimBoard WHERE a.id=(:no)")
	long picturecount(@Param("no") long no);

}
