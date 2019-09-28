package us.flower.dayary.domain;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import us.flower.dayary.domain.common.DateAudit;
/**
 * 커뮤니티게시판
 *   by choiseongjun
 */
@Entity
@Table(name="COMMUNITY_BOARD")
@Data
public class CommunityBoard{

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
	
	   // 생성일자
    @Column(name = "CREATE_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    // 수정일자
    @Column(name = "UPDATE_DATE", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_GROUP_NO")
    private BoardGroup boardGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEOPLE_ID")
    private People people;
}
