package us.flower.dayary.repository.moim.picture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import us.flower.dayary.domain.MoimBoardReply;
import static us.flower.dayary.domain.QMoimBoardReply.moimBoardReply;

import java.util.List;

public class MoimBoardReplyRepositoryCustomImpl extends QuerydslRepositorySupport implements MoimBoardReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MoimBoardReplyRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(MoimBoardReply.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<MoimBoardReply> findAllReply(long boardId) {
        List<MoimBoardReply> parents = queryFactory
                .selectFrom(moimBoardReply)
                .leftJoin(moimBoardReply.child, moimBoardReply)
                .where(moimBoardReply.moimBoard.id.eq(boardId))
                .fetchJoin()
                .fetch();
        return parents;
    }
}

