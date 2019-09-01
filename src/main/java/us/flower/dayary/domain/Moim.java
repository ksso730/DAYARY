package us.flower.dayary.domain;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

/**
 * 온라인모임
 */
@Entity
@Table(name = "MOIM")
@Data
@Builder
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
    @JoinColumn(name = "PEOPLE_NO", referencedColumnName = "NO")
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

    /*
     * 모임 참여자 예정
     * @OneToMany(mappedBy = "moim", fetch = FetchType.EAGER)
     *
     * @JsonManagedReference private List<MoimPeople> moimPeopleList;
     */

}
