package us.flower.dayary.domain;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name="MOIM_TODO_WRITE")
@SequenceGenerator(
        name="MOIM_TODO_WRITE_GEN", //시퀀스 제너레이터 이름
        sequenceName="MOIM_TODO_WRITE_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoWrite extends DateAudit{
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MOIM_TODO_WRITE_GEN" //식별자 생성기를 설정해놓은  GEN으로 설정        
            )
	@Column(name="ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="MOIM_ID")
	private Moim moim;
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	private People people;
	
	@Column(name="PLAN_TITLE")
	private String plan_title;
	
	@JsonFormat(pattern="mm/DD/yyyy")
	@Column(name="FROM_DATE")
	private Date from_date;
	@JsonFormat(pattern="mm/DD/yyyy")
	@Column(name="TO_DATE")
	private Date to_date;
	
	@Column(name="CREATE_DATE")
	private Date create_date;
	
}
