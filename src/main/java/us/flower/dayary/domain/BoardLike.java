package us.flower.dayary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;

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
