package us.flower.dayary.repository.community;

import us.flower.dayary.domain.CommunityBoardReply;

import java.util.List;

public interface BoardReplyRepositoryCustom {

    // 게시글 댓글중 삭제된거 제외하고 (parent + child)
    List<CommunityBoardReply> findAllReply(long boardId);
}
