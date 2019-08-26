package us.flower.dayary.domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="PERSON")
@Data
public class Person {

	@Id
	@GeneratedValue
	@Column(name="PERSON_NO")
	private String Person_NO;
	@Column(name="PERSON_ID")
	private String Person_ID;
	@Column(name="PERSON_PASSWORD")
	private String Person_Password;
	@Column(name="PERSON_NAME")
	private String Person_Name;
	
}
