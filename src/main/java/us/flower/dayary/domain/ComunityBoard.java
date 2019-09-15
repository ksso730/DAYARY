package us.flower.dayary.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="COMUNITY_BOARD")
@Data
public class ComunityBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="NO")
	private long no;
	@Column(name="TITLE")
	private String title;
	@Column(name="MEMO")
	private String memo;
	@Column(name="DELETEFLAG" ,nullable=false, columnDefinition = "char(1) default 'N'")
	private char deleteFlag;
	@Column(name="HEART")
	private long heart;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_GROUP_NO")
    private BoardGroup boardGroup;
}
