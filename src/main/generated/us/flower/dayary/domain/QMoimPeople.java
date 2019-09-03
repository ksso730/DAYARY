package us.flower.dayary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoimPeople is a Querydsl query type for MoimPeople
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoimPeople extends EntityPathBase<MoimPeople> {

    private static final long serialVersionUID = -1376161600L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoimPeople moimPeople = new QMoimPeople("moimPeople");

    public final QMoim moim;

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final QPeople people;

    public QMoimPeople(String variable) {
        this(MoimPeople.class, forVariable(variable), INITS);
    }

    public QMoimPeople(Path<? extends MoimPeople> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoimPeople(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoimPeople(PathMetadata metadata, PathInits inits) {
        this(MoimPeople.class, metadata, inits);
    }

    public QMoimPeople(Class<? extends MoimPeople> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.moim = inits.isInitialized("moim") ? new QMoim(forProperty("moim"), inits.get("moim")) : null;
        this.people = inits.isInitialized("people") ? new QPeople(forProperty("people")) : null;
    }

}

