package us.flower.dayary.domain;

import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;

import javax.persistence.*;

/**
 * 오프라인모임참가자
 *  * by choiseongjun
 */
@Entity
@Table(name="MEETUP_PEOPLE")
@Data
public class MeetupPeople extends DateAudit{

    @Id
    @GeneratedValue
    @Column(name="NO")
    private long no;

    @Column(name="MOIM_NO")
    private long moimNo;

    @Column(name="MEETUP_PEOPLE_NO")
    private long meetupPeopleNo;

    @Column(name="PEOPLE_NO")
    private long peopleNo;
}
