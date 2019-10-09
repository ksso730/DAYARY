package us.flower.dayary.repository.community;

import us.flower.dayary.domain.CommunityBoardReply;

import java.util.List;

public interface BoardReplyRepositoryCustom {
    List<CommunityBoardReply> findAllReply();
}
