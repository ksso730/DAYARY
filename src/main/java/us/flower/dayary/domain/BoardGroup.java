package us.flower.dayary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;
/**
 * 게시판그룹
 *   by choiseongjun
 */
@Entity
@Table(name="BOARD_GROUP")
@SequenceGenerator(
        name="BOARD_GROUP_GEN", //시퀀스 제너레이터 이름
        sequenceName="BOARD_GROUP_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
@Data
public class BoardGroup{

	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="BOARD_GROUP_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정        
            )
	@Column(name="NO")
	private long no;
	
	@Column(name="NAME")
	private String name;
	
//	@Column(name="USE_FLAG")
//	private char useFlag;
//	@Column(name="DELETE_FLAG")
//	private char deleteFlag;
//	@Column(name="READ_ONLY")
//	private char readOnly;
	
  @OneToMany(mappedBy = "boardGroup",cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore 
  private List<ComunityBoard> communityBoard=new ArrayList<>();
}
