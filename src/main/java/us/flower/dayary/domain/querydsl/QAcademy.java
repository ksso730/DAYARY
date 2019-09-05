package us.flower.dayary.domain.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import us.flower.dayary.domain.Academy;


/**
 * QAcademy is a Querydsl query type for Academy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAcademy extends EntityPathBase<Academy> {

    private static final long serialVersionUID = 1639978625L;

    public static final QAcademy academy = new QAcademy("academy");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QAcademy(String variable) {
        super(Academy.class, forVariable(variable));
    }

    public QAcademy(Path<? extends Academy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcademy(PathMetadata metadata) {
        super(Academy.class, metadata);
    }

}

