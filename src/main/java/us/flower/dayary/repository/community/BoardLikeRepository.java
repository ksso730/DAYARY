package us.flower.dayary.repository.community;


import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.BoardLikeId;


public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {

    // 좋아요 이력 조회
    BoardLike findBoardLikeById(BoardLikeId boardLikeId);

}
