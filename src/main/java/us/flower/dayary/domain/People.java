package us.flower.dayary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * 회원
 */
@Entity
@Table(name="PEOPLE")
@Data
@Builder
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

	@Column(name="CLOSE")
	private boolean close;
	
	@OneToMany(mappedBy = "people")
	private List<Moim> moims  = new ArrayList<Moim>();
	

}
