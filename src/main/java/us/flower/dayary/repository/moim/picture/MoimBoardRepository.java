package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;

public interface MoimBoardRepository extends JpaRepository<MoimBoard, Long> {

	List<MoimBoard> findByToDoWriteList_id(long id);

	void deleteByToDoWriteList_id(long id);
 
	@Query(value = "select m from MoimBoard m inner join  m.moimBoardfile f WHERE m.moim=(:moim) and  m.boardGroup=(:boardGroup) and f.representImage = (:representImage)",countQuery = "select count(m) from MoimBoard m")
	Page<MoimBoard> searchRepresent(@Param("boardGroup") BoardGroup boardGroup,@Param("moim") Moim moim,@Param("representImage") long representImage,Pageable page);

	//public Page<MoimBoardImage> search(final Pageable pageable);

	List<MoimBoard> findByBoardGroup(BoardGroup board_group_id);
}
