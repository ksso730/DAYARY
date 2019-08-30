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

    @Column(name="PEOPLE_NO")
    private String peopleNo;

    @Column(name="MOIM_NO")
    private String moimNo;
}
