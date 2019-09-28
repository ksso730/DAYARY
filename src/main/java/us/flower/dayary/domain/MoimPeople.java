package us.flower.dayary.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

/**
 * 온라인모임참가자
 * by choiseongjun
 */
@Entity
@Table(name = "MOIM_PEOPLE")
@SequenceGenerator(
        name="MOIM_PEOPLE_GEN", //시퀀스 제너레이터 이름
        sequenceName="MOIM_PEOPLE_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople extends DateAudit{

	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MOIM_PEOPLE_SEQ" //식별자 생성기를 설정해놓은  GEN으로 설정        
            )
	@Column(name = "ID")
	private long id;
	@ManyToOne
	@JoinColumn(name = "MOIM_ID")
	@JsonIgnore
	private Moim moim;
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	@JsonIgnore
	private People people;
	 
	@Column(name="JOINROLE")
	private String joinrole;

}
