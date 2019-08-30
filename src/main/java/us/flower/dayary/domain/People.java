package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 회원
 */
@Entity
@Table(name="PEOPLE")
@Data
public class People {

	@Id
	@GeneratedValue
	@Column(name="NO")
	private long no;
	@Column(name="ID")
	private String id;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="NAME")
	private String name;
	@Column(name="PHOTO")
	private String photo;
	
}
