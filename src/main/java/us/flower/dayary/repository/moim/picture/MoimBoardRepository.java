package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.MoimBoard;

public interface MoimBoardRepository extends JpaRepository<MoimBoard, Long>{
	
	 List<MoimBoard> findByToDoWriteList_id(long id);
	 void deleteByToDoWriteList_id(long id);
}
