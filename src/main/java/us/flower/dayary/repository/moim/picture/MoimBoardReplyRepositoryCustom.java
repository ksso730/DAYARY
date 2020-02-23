package us.flower.dayary.repository.moim.picture;

import us.flower.dayary.domain.MoimBoardReply;

import java.util.List;

public interface MoimBoardReplyRepositoryCustom {
    // 게시글 댓글중 삭제된거 제외하고 (parent + child)
    List<MoimBoardReply> findAllReply(long boardId);
}
