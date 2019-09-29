package us.flower.dayary.repository.community;


import org.springframework.data.jpa.repository.JpaRepository;
import us.flower.dayary.domain.BoardLike;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {

}
