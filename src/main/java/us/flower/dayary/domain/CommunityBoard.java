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

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = People.class)
	@JoinColumn(name = "PEOPLE_ID", nullable = false)
	private People people;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardGroup.class)
	@JoinColumn(name = "BOARD_GROUP_ID", nullable = false)
	private BoardGroup boardGroup;

	@Column(name="TITLE")
	private String title;

	@Column(name="MEMO", nullable = false)
	@Lob
	private String memo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "communityBoard")
	private List<File> files;

	@Column(name="DELETEFLAG" ,nullable=false, columnDefinition = "char(1) default 'N'")
	private char deleteFlag;

	@Column(name="VIEW_COUNT")
	private long viewCount;

	@Column(name="HEART")
	private long heart;
}
