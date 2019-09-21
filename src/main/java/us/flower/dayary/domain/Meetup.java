package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 오프라인모임
 *   by choiseongjun
 */
@Entity
@Table(name="MEETUP")
@Data
public class Meetup {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;
}
