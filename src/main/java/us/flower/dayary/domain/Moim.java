package us.flower.dayary.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 온라인모임
 */
@Entity
@Table(name="MOIM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moim {

	@Id
	@GeneratedValue
	@Column(name="NO")
	private long no;

	//category manytoone, 외래키로 사용 예정
	@Column(name="CATEGORY_NO")
	private long categoryNo;

	//모임제목
	@Lob //길이 제한 없음
	@Column(name="TITLE")
	private String title;

	//모임소개
	@Column(name="INTRO")
	private String intro;

	//모임 인원수 제한
	@Column(name="PEOPLE_LIMIT", length = 3)
	private int peopleLimit;
	
	//모임 생성일
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	//사용자번호 
	@ManyToOne
	@JoinColumn(name ="PEOPLE")
	private People people;
	
	/* 
	 * 모임 참여자 예정
	 * @OneToMany(mappedBy = "moim", fetch = FetchType.EAGER)
	 * 
	 * @JsonManagedReference private List<MoimPeople> moimPeopleList;
	 */
}
