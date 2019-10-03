package us.flower.dayary.domain;

import javax.persistence.*;

import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name="BOARD_LIKE")
@Data
public class BoardLike extends DateAudit {

    @EmbeddedId
    private BoardLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="BOARD_GROUP_ID")
    private long boardGroupId;

}
