package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 오프라인모임참가자
 */
@Entity
@Table(name="OFFLINE_MOIM_PEOPLE")
@Data
public class OfflineMoimPeople {

    @Column(name="MOIM_NO")
    private long moimNo;

    @Column(name="OFFLINE_MOIM_NO")
    private long offlineMoimNo;

    @Column(name="PEOPLE_NO")
    private long peopleNo;
}
