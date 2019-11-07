//package us.flower.dayary.repository.moim.picture;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.JPQLQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//import us.flower.dayary.domain.MoimBoard;
//import static us.flower.dayary.domain.QMoimBoard.moimBoard;
//import static us.flower.dayary.domain.QMoimBoardFile.moimBoardFile;
//import us.flower.dayary.domain.DTO.MoimBoardImage;
//
//
//public class MoimBoardRepositoryCustomImpl extends QuerydslRepositorySupport implements MoimBoardRepositoryCustom  {
//	
//	private final JPAQueryFactory jpaQueryFactory;
//	
//	public MoimBoardRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
//		super(MoimBoard.class);
//		 this.jpaQueryFactory = jpaQueryFactory;
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public Page<MoimBoardImage> search(final Pageable pageable) {
////		final QMoimBoard moimBoard = QMoimBoard.moimBoard;
////		final QMoimBoardFile moimBoardFile = QMoimBoardFile.moimBoardFile;
//		System.out.println("1");
//		final JPQLQuery<MoimBoardImage> query;
//		query = from(moimBoard)
//				.join(moimBoardFile).on(moimBoard.id.eq(moimBoardFile.moid_moard.id))
//				.select(Projections.constructor(MoimBoardImage.class,
//						moimBoard.id,
//						moimBoard.people.id,
//                        moimBoard.boardGroup.id,
//                        moimBoard.moim.id,
//                        moimBoard.title,
//                        moimBoard.memo,
//                        moimBoard.create_date,
//                        moimBoard.update_date,
//                        moimBoard.delete_flag,
//                        moimBoard.heart,
//                        moimBoardFile.file_locate))
//                 .where(moimBoard.id.gt(0).and(moimBoardFile.representImage.eq((long) 0)));
//		final List<MoimBoardImage> accounts = getQuerydsl().applyPagination(pageable, query).fetch();
//	        
//		return new PageImpl<>(accounts, pageable, query.fetchCount());
//	}
//
//}
