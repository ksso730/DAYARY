package us.flower.dayary.repository.community;


import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.BoardLikeId;
import us.flower.dayary.domain.CommunityBoardReply;


public interface BoardReplyRepository extends JpaRepository<CommunityBoardReply,Long> {

}
