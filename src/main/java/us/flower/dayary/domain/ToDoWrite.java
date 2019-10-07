package us.flower.dayary.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name="MOIM_TODO_WRITE")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoWrite extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	private String count;
	
	private double progress;
	
	@Column(name="STATUS",  columnDefinition = "String default 'New'")
	   private String status;


}
