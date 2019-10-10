package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BoardLikeId implements Serializable {

    @Column(name="PEOPLE_ID")
    private long peopleId;

    @Column(name ="COMMUNITY_BOARD_ID")
    private long communityBoardId;

    @Column(name="BOARD_GROUP_ID")
    private  long boardGroupId;
}
