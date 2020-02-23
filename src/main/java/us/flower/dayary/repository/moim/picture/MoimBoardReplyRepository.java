package us.flower.dayary.repository.moim.picture;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardReply;

import java.util.List;

public interface MoimBoardReplyRepository extends JpaRepository<MoimBoardReply,Long>, MoimBoardReplyRepositoryCustom {
    List<MoimBoardReply> getAllByMoimBoardAndDeleteFlagAndParentIsNull(MoimBoard moimBoard, String deleteFlag, Pageable pageable);
}