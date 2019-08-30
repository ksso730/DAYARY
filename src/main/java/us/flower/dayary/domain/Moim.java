package us.flower.dayary.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
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

	@Column(name="CATEGORY_NO")
	private long categoryNo;

	@Column(name="TITLE")
	private String title;

	@Column(name="INTRO")
	private String intro;

	@Column(name="PEOPLE_LIMIT")
	private int peopleLimit;

	@OneToMany(mappedBy = "moim", fetch = FetchType.EAGER)
	private List<MoimPeople> moimPeopleList;
}
