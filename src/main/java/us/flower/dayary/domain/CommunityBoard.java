package us.flower.dayary.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;
import us.flower.dayary.domain.common.DateAudit;
import java.util.List;


@Entity
@Table(name="COMMUNITY_BOARD")
@Data
@ToString(exclude = "communityBoardReplies")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "communityBoard")
//	private List<File> files;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "communityBoard", cascade = CascadeType.ALL)
	private List<CommunityBoardReply> communityBoardReplies;

	@Column(name="DELETE_FLAG" ,nullable=false, columnDefinition = "VARCHAR(1) default 'N'")
	private char deleteFlag;

	@Column(name="VIEW_COUNT")
	private long viewCount;

	@Column(name="HEART")
	private long heart;
	
	@ManyToOne
	@JoinColumn(name = "MOIM_TODO_WRITE_LIST_ID")
	private ToDoWriteList toDoWriteList;
}
