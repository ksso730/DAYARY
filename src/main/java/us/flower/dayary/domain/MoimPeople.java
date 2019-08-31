package us.flower.dayary.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * 온라인모임참가자 
 */
@Entity
@Table(name="MOIM_PEOPLE")
@Data
@ToString(exclude = "moim")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople {

    @Id
    @GeneratedValue
    @Column(name="NO")
    private long no;

	/* moim에서 주석처리해놔서 우선 같이 주석
	 * @ManyToOne
	 * 
	 * @JoinColumn(name ="MOIM", referencedColumnName = "NO")
	 * 
	 * @JsonBackReference private Moim moim;
	 */

    @Column(name="PEOPLE_NO")
    private long peopleNo;

    @Column(name="ROLE")
    private String role;

}
