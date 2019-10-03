package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class CommunityBoardReplyId implements Serializable {

    @Column(name="PEOPLE_ID")
    private long peopleId;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_BOARD_ID")
    private CommunityBoard communityBoard;

    @Column(name="BOARD_GROUP_ID")
    private  long boardGroupId;
}
