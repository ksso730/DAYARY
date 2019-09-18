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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 온라인모임
 * by choiseongjun
 */
@Entity
@Table(name = "MOIM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moim {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO")
    private long no;

    //모임제목
    @Column(name = "TITLE")
    private String title;

    //모임소개
    @Lob //길이 제한 없음
    @Column(name = "INTRO")
    private String intro;

    //모임 인원수 제한
    @Column(name = "PEOPLE_LIMIT", length = 3)
    private int peopleLimit;

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

    //모임 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NO")
    private Category category;

    //이미지경로
	@Column(name = "IMAGE_PATH")
    private String imagePath;

	//이미지이름
	@Column(name = "IMAGE_NAME")
    private String imageName;

	//이미지확장자
	@Column(name = "IMAGE_EXTENSION")
    private String imageExtension;

    
    // 모임 참여자 예정
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "MOIM_PEOPLE",
               joinColumns = @JoinColumn(name = "MOIM_NO"),
               inverseJoinColumns = @JoinColumn(name = "PEOPLE_ID"))
    private List<People> peopleList;



}
