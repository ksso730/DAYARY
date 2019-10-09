package us.flower.dayary.repository.community;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import us.flower.dayary.domain.CommunityBoardReply;

import java.util.List;

import static us.flower.dayary.domain.QCommunityBoardReply.communityBoardReply;

public class BoardReplyRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public BoardReplyRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(CommunityBoardReply.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public List<CommunityBoardReply> findAllReply() {
        return queryFactory.selectFrom(communityBoardReply)
                .where(communityBoardReply.parent.isNull())
                .fetch();
    }
}
