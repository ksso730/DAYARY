package us.flower.dayary.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import us.flower.dayary.domain.common.DateAudit;

import java.util.List;

/**
 * 커뮤니티게시판
 *   by choiseongjun
 */
@Entity
@Table(name="COMMUNITY_BOARD")
@Data
public class CommunityBoard extends DateAudit{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	@Column(name="TITLE")
	private String title;
	@Column(name="MEMO")
	private String memo;
	@Column(name="DELETEFLAG" ,nullable=false, columnDefinition = "char(1) default 'N'")
	private char deleteFlag;
	@Column(name="HEART")
	private long heart;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_GROUP_NO")
    private BoardGroup boardGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEOPLE_ID")
    private People people;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "communityBoard")
	private List<File> files;
}
