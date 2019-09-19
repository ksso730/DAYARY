package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard,Long>{



	void deleteByboardGroupAndNo(long board_group_no, long timeLineListNo);
 
}
