package us.flower.dayary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMeetup is a Querydsl query type for Meetup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeetup extends EntityPathBase<Meetup> {

    private static final long serialVersionUID = -17207571L;

    public static final QMeetup meetup = new QMeetup("meetup");

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public QMeetup(String variable) {
        super(Meetup.class, forVariable(variable));
    }

    public QMeetup(Path<? extends Meetup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeetup(PathMetadata metadata) {
        super(Meetup.class, metadata);
    }

}

