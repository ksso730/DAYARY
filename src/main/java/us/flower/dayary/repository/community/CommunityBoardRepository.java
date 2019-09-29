package us.flower.dayary.repository.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard,Long>{


    Page<CommunityBoard> findAllByBoardGroupAndDeleteFlag(BoardGroup boardGroup, char deleteFlag, Pageable pageable);
    //Long countByBoardGroupAndDeleteFlag(BoardGroup boardGroup, char deleteFlag);
}
