package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 참여자
 */
@Entity
@Table(name="MOIM_PEOPLE")
@Data
public class MoimPeople {

	@Id
	@GeneratedValue
	@Column(name="PEOPLE_NO")
	private String peopleNo;
	@Column(name="MOIM_NO")
	private String moimNo;
	
}
