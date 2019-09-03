package us.flower.dayary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMoimPeople is a Querydsl query type for MoimPeople
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoimPeople extends EntityPathBase<MoimPeople> {

    private static final long serialVersionUID = -1376161600L;

    public static final QMoimPeople moimPeople = new QMoimPeople("moimPeople");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public QMoimPeople(String variable) {
        super(MoimPeople.class, forVariable(variable));
    }

    public QMoimPeople(Path<? extends MoimPeople> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMoimPeople(PathMetadata metadata) {
        super(MoimPeople.class, metadata);
    }

}

