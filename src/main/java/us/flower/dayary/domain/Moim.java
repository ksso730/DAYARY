package us.flower.dayary.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 온라인모임
 */
@Entity
@Table(name="MOIM")
@Data
public class Moim {

	@Id
	@GeneratedValue
	@Column(name="NO")
	private long no;

	@Column(name="CATEGORY_NO")
	private long categoryNo;

	@Column(name="TITLE")
	private String title;

	@Column(name="INTRO")
	private String intro;

	@Column(name="PEOPLE_LIMIT")
	private String peopleLimit;
	
	
}
