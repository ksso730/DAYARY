package us.flower.dayary.domain;

import javax.persistence.*;
import lombok.Data;
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
	@Lob
	private String memo;
	@Column(name="DELETEFLAG" ,nullable=false, columnDefinition = "char(1) default 'N'")
	private char deleteFlag;
	@Column(name="HEART")
	private long heart;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardGroup.class)
    @JoinColumn(name = "BOARD_GROUP_ID")
    private BoardGroup boardGroup;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = People.class)
    @JoinColumn(name = "PEOPLE_ID")
    private People people;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "communityBoard")
	private List<File> files;

	@Column(name="VIEW_COUNT")
	private long viewCount;

	@Column(name="LIKE_COUNT")
	private long likeCount;
}
