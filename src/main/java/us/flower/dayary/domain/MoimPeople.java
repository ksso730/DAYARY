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
@Table(name="MOIM_PEOPLE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople {

    @Id
    @GeneratedValue
    @Column(name="NO")
    private long no;

    @ManyToOne
    @JoinColumn(name ="MOIM", referencedColumnName = "NO")
    private Moim moim;

    @Column(name="PEOPLE_NO")
    private long peopleNo;
}
