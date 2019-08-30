package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 오프라인모임참가자
 */
@Entity
@Table(name="MEETUP_PEOPLE")
@Data
public class MeetupPeople {

    @Column(name="MOIM_NO")
    private long moimNo;

    @Column(name="MEETUP_PEOPLE_NO")
    private long meetupPeopleNo;

    @Column(name="PEOPLE_NO")
    private long peopleNo;
}
