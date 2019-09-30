package us.flower.dayary.repository.community;


import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {

    BoardLike findBoardLikeByBoardAndPeople(CommunityBoard communityBoard, People people);
}
