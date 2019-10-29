package us.flower.dayary.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

/**
 * 온라인모임
 * by choiseongjun
 */
@Entity
@Table(name = "MOIM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moim extends DateAudit{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    
    //모임 카테고리
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COMM_CODE")
    private Common category;
    
    //모임제목
    @Column(name = "TITLE")
    private String title;

    //모임소개
    @Lob //길이 제한 없음
    @Column(name = "INTRO")
    private String intro;

    //모임 인원수 제한
    @Column(name = "PEOPLE_LIMIT", length = 3)
    private long peopleLimit;

    //모임 생성일자
    @Column(name = "CREATE_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    //모임 수정일자
    @Column(name = "UPDATE_DATE", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    //사용자번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEOPLE_ID", referencedColumnName = "ID")
    private People people;

    //이미지경로
	@Column(name = "IMAGE_PATH", nullable = true)
    private String imagePath;

	//이미지이름
	@Column(name = "IMAGE_NAME", nullable = true)
    private String imageName;

	//이미지확장자
	@Column(name = "IMAGE_EXTENSION", nullable = true)
    private String imageExtension;
	
	//시/도
	@Column(name = "SIDO_CODE")
    private String sidocode;
	
	//구
	@Column(name = "SIGOON_CODE")
    private String sigooncode;
	//시/도
	@Column(name = "SIDO_NAME")
    private String sidoname;
	//구
	@Column(name = "SIGOON_NAME")
    private String sigoonname;	
    
    // 모임 참여자 
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MOIM_PEOPLE",
               joinColumns = @JoinColumn(name = "MOIM_ID"),
               inverseJoinColumns = @JoinColumn(name = "PEOPLE_ID"))
    private List<People> peopleList;
	@OneToMany(fetch = FetchType.LAZY,orphanRemoval=true,mappedBy = "moim")
	@JsonIgnore
	private List<ToDoWrite> todowrite;
	@OneToMany(fetch = FetchType.LAZY,orphanRemoval=true,mappedBy = "moim")
	@JsonIgnore
	private List<ToDoWriteList> todowriteList;
	@OneToMany(fetch = FetchType.LAZY,orphanRemoval=true,mappedBy = "moim")
	@JsonIgnore
	private List<Meetup> meetup;
	
	//가입조건  N은 누구나 Y는 승인해야함
	@Column(name="JOIN_CONDITION" ,nullable=false, columnDefinition = "char(1) default 'N'")
	private char joinCondition;

}
