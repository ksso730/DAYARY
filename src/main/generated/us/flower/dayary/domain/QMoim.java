package us.flower.dayary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoim is a Querydsl query type for Moim
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMoim extends EntityPathBase<Moim> {

    private static final long serialVersionUID = -1608944943L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoim moim = new QMoim("moim");

    public final QCategory category;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath imageExtension = createString("imageExtension");

    public final StringPath imageName = createString("imageName");

    public final StringPath imagePath = createString("imagePath");

    public final StringPath intro = createString("intro");

    public final ListPath<MoimPeople, QMoimPeople> moimPeopleList = this.<MoimPeople, QMoimPeople>createList("moimPeopleList", MoimPeople.class, QMoimPeople.class, PathInits.DIRECT2);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final QPeople people;

    public final NumberPath<Integer> peopleLimit = createNumber("peopleLimit", Integer.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QMoim(String variable) {
        this(Moim.class, forVariable(variable), INITS);
    }

    public QMoim(Path<? extends Moim> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoim(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoim(PathMetadata metadata, PathInits inits) {
        this(Moim.class, metadata, inits);
    }

    public QMoim(Class<? extends Moim> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.people = inits.isInitialized("people") ? new QPeople(forProperty("people")) : null;
    }

}

