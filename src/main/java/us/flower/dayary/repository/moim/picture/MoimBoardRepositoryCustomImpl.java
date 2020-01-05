package us.flower.dayary.repository.moim.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.QMoimBoard;
import us.flower.dayary.domain.DTO.BoardListDTO;
import us.flower.dayary.domain.DTO.MoimBoardImage;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;

import static us.flower.dayary.domain.QCommunityBoard.communityBoard;
import static us.flower.dayary.domain.QCommunityBoardReply.communityBoardReply;
import static us.flower.dayary.domain.QMoimBoard.moimBoard;

public class MoimBoardRepositoryCustomImpl extends QuerydslRepositorySupport implements MoimBoardRepositoryCustom  {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public MoimBoardRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
		super(MoimBoard.class);
		 this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public Page<MoimBoardImage> search(Pageable pageable) {
		return null;
	}

//	@Override
//	public Page<MoimBoardListDTO> findMoimBoardList(BoardGroup boardGroup, Pageable pageable) {
//		
//		final JPQLQuery<MoimBoardListDTO> query = from(moimBoard).select(Projections.constructor(BoardListDTO.class,
//                                        moimBoard.id,
//                                        moimBoard.title,
//                                        moimBoard.people.name,
//                                        moimBoard.create_date,
//                                        moimBoard.heart))
//										.from(moimBoard)
//										.where(moimBoard.boardGroup.eq(boardGroup));
//																						
//		
//		return null;
//	}


}
