package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 온라인모임참가자
 */
@Entity
@Table(name="ONLINE_MOIM_PEOPLE")
@Data
public class OnlineMoimPeople {

    @Column(name="MOIM_NO")
    private long moimNo;

    @Column(name="PEOPLE_NO")
    private long peopleNo;


}
