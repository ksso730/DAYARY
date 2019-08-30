package us.flower.dayary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 온라인모임참가자
 */
@Entity
@Table(name="moim_people")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople {

    @Id
    @GeneratedValue
    @Column(name="no")
    private long no;

    @ManyToOne
    @JoinColumn(name ="moim", referencedColumnName = "no")
    private Moim moim;

    @Column(name="people_no")
    private long peopleNo;

}
