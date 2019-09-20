package us.flower.dayary.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

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
                "email"
            })
    })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People extends DateAudit{
 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	@Column(name="EMAIL")
	private String email;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="NAME")
	private String name;

	@Column(name="PHOTO")
	private String photo;

	@Column(name="ACTIVATION")
	private String activation;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", 
            joinColumns = @JoinColumn(name = "PEOPLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();
//	// 회원  참여자 예정
//    @OneToMany(mappedBy = "people")
//    @JsonIgnore 
//    private List<MoimPeople> moimPeopleList=new ArrayList<>();

    public People(String email,String password,String name,String photo,String activation) {
    	this.email=email;
    	this.password=password;
    	this.name=name;
    	this.photo=photo;
    	this.activation=activation;
    }
	
}
