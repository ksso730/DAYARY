package us.flower.dayary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="MOIM")
@Data
public class Moim {

	
	@Id
	@GeneratedValue
	@Column(name="MOIM_NO")
	private String moim_No;
	@Column(name="MOIM_TITLE")
	private String moim_title;
	@Column(name="MOIM_STORY")
	private String moim_Story;
	
	
}
