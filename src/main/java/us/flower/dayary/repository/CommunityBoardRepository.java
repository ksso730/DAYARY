package us.flower.dayary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard,Long>{


    Page<CommunityBoard> findAllByBoardGroup(BoardGroup boardGroup, Pageable pageable);
    Long countByBoardGroupIs(BoardGroup boardGroup);
}
