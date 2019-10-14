package us.flower.dayary.repository.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.DTO.BoardListDTO;

public interface CommunityBoardRepositoryCustom {

    // 게시글 목록 + 페이징 + 삭제된 댓글 제외
    Page<BoardListDTO> findAllByBoardGroupAndDeleteFlagAndReply(BoardGroup boardGroup, String deleteFlag, Pageable pageable, String search);

}
