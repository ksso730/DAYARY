package us.flower.dayary.domain.queryDsl;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import us.flower.dayary.domain.People;


/**
 * QPeople is a Querydsl query type for People
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPeople extends EntityPathBase<People> {

    private static final long serialVersionUID = 68973658L;

    public static final QPeople people = new QPeople("people");

    public final StringPath activation = createString("activation");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final StringPath password = createString("password");

    public final StringPath photo = createString("photo");

    public QPeople(String variable) {
        super(People.class, forVariable(variable));
    }

    public QPeople(Path<? extends People> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPeople(PathMetadata metadata) {
        super(People.class, metadata);
    }

}

