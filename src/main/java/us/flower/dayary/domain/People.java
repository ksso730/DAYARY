package us.flower.dayary.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*회원
* @param 
* @return
* @throws 
* @date Role 추가  2019-09-17
* @author choiseongjun
*/
@Entity
@Table(name="PEOPLE",uniqueConstraints = {
      
            @UniqueConstraint(columnNames = {
                "id"
            })
    })
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
	
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "USERS_ROLES",
	            joinColumns = @JoinColumn(name = "PEOPLE_ID"),
	            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	    private Set<Role> roles = new HashSet<>();
//	// 회원  참여자 예정
//    @OneToMany(mappedBy = "people")
//    @JsonIgnore 
//    private List<MoimPeople> moimPeopleList=new ArrayList<>();
}
