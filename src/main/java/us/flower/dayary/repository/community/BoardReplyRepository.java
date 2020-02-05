package us.flower.dayary.repository.community;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.BoardLikeId;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.CommunityBoardReply;

import java.util.List;


public interface BoardReplyRepository extends JpaRepository<CommunityBoardReply,Long>, BoardReplyRepositoryCustom {
    List<CommunityBoardReply> getAllByCommunityBoardAndDeleteFlagAndParentIsNull(CommunityBoard communityBoard, String deleteFlag, Pageable pageable);
}
