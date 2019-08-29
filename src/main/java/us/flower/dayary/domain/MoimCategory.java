package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 모임카테고리
 */
@Entity
@Table(name="MOIM_CATEGORY")
@Data
public class MoimCategory {

    @Id
    @GeneratedValue
    @Column(name="NO")
    private String no;
    @Column(name="NAME")
    private String name;
}
