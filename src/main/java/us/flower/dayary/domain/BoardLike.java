package us.flower.dayary.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="BOARD_LIKE")
@Data
public class BoardLike extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PEOPLE_ID")
    private People people;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private CommunityBoard board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_GROUP_ID")
    private BoardGroup boardGroup;

}
