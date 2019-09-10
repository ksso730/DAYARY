package us.flower.dayary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원
 */
@Entity
@Table(name="PEOPLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {
 
	@Id
	@GeneratedValue
	@Column(name="NO")
	private long no;

	@Column(name="ID")
	private String id;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="NAME")
	private String name;

	@Column(name="PHOTO")
	private String photo;

	@Column(name="ACTIVATION")
	private String activation;
//	// 회원  참여자 예정
//    @OneToMany(mappedBy = "people")
//    @JsonIgnore 
//    private List<MoimPeople> moimPeopleList=new ArrayList<>();
}
