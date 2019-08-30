package us.flower.dayary.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 온라인모임
 */
@Entity
@Table(name="moim")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moim {

	@Id
	@GeneratedValue
	@Column(name="no")
	private long no;

	@Column(name="category_no")
	private long categoryNo;

	@Column(name="title")
	private String title;

	@Column(name="intro")
	private String intro;

	@Column(name="people_limit")
	private int peopleLimit;

	@OneToMany(mappedBy = "moim", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MoimPeople> moimPeopleList;
}
