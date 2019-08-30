package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 카테고리[오라인모임/오프라인모임]
 */
@Entity
@Table(name="CATEGORY")
@Data
public class Category {

    @Id
    @GeneratedValue
    @Column(name="NO")
    private long no;

    @Column(name="NAME")
    private String name;
}
