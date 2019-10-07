package us.flower.dayary.repository.querydsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import us.flower.dayary.domain.CommunityBoardReply;

import java.util.List;

@Repository
public class BoardReplyRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public BoardReplyRepositorySupport(JPAQueryFactory queryFactory) {
        super(CommunityBoardReply.class);
        this.queryFactory = queryFactory;
    }

    public List<CommunityBoardReply> findReply(){
        return queryFactory.selectFrom(communityBoardReply);
    }
}
