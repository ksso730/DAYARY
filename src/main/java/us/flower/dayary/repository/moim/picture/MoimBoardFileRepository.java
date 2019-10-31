package us.flower.dayary.repository.moim.picture;


import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.MoimBoardFile;

public interface MoimBoardFileRepository extends JpaRepository<MoimBoardFile, Long>{
	MoimBoardFile findByMoimBoard_id(long id);
}
