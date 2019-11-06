package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.QMoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardImage;

public interface MoimBoardRepository extends CrudRepository<MoimBoard, Long>, MoimBoardRepositoryCustom{
	public default Predicate makePredicate(String type,String keyword)
	{
		BooleanBuilder builder = new BooleanBuilder();
		QMoimBoard moimBoard = QMoimBoard.moimBoard;
		
		builder.and(moimBoard.id.gt(0));
		
		return builder;
	}
	public Page<MoimBoardImage> search(final Pageable pageable);
	List<MoimBoard> findByBoardGroup(BoardGroup board_group_id);
	@Query(value = "select m from MoimBoard m inner join  m.moimboardfile f WHERE m.moim=(:moim) and  m.boardGroup=(:boardGroup) and f.representImage = (:representImage)",countQuery = "select count(m) from MoimBoard m")
	Page<MoimBoard> searchRepresent(@Param("boardGroup") BoardGroup boardGroup,@Param("moim") Moim moim,@Param("representImage") long representImage,Pageable page);
}
