package us.flower.dayary.domain.queryDsl;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import us.flower.dayary.domain.MeetupPeople;


/**
 * QMeetupPeople is a Querydsl query type for MeetupPeople
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeetupPeople extends EntityPathBase<MeetupPeople> {

    private static final long serialVersionUID = -1793398820L;

    public static final QMeetupPeople meetupPeople = new QMeetupPeople("meetupPeople");

    public final NumberPath<Long> meetupPeopleNo = createNumber("meetupPeopleNo", Long.class);

    public final NumberPath<Long> moimNo = createNumber("moimNo", Long.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final NumberPath<Long> peopleNo = createNumber("peopleNo", Long.class);

    public QMeetupPeople(String variable) {
        super(MeetupPeople.class, forVariable(variable));
    }

    public QMeetupPeople(Path<? extends MeetupPeople> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeetupPeople(PathMetadata metadata) {
        super(MeetupPeople.class, metadata);
    }

}

