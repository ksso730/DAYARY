package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;

import us.flower.dayary.domain.MoimBoard;
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
}
