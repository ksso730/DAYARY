package us.flower.dayary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="MOIM_PERSON")
@Data
public class MoimPerson {

	@Column(name="MOIM_PERSON_NO")
	@Id
	@GeneratedValue
	private String moimPersonNo;
	
}
