package us.flower.dayary.repository.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;

import java.util.List;


/**
 * 리포지토리 작명규칙
 * FIND / SAVE / REMOVE / UPDATE
 */
public interface CommunityBoardRepository extends JpaRepository<CommunityBoard,Long>{

    // 게시글 목록 + 페이징
    Page<CommunityBoard> findAllByBoardGroupAndDeleteFlag(BoardGroup boardGroup, char deleteFlag, Pageable pageable);

    // 게시글 목록 전체 (타임라인)
    List<CommunityBoard> findAllByBoardGroupAndDeleteFlag(BoardGroup boardGroup, char deleteFlag);

    // 본인글 목록 전체 (타임라인)
    List<CommunityBoard> findAllByBoardGroupAndDeleteFlagAndPeople(BoardGroup boardGroup, char deleteFlag, People people);

}
