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
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private long no;
    @ManyToOne
    @JoinColumn(name = "MOIM_NO")
    private Moim moim;
    @ManyToOne
    @JoinColumn(name = "PEOPLE_NO")
    private People people;
    
}


