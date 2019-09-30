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

import org.hibernate.annotations.ColumnDefault;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name="MOIM_TODO_WRITE_LIST")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoWriteList extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="MOIM_TO_DO_WRITE_ID")
	private ToDoWrite toDoWrite;
	
	@ManyToOne
	@JoinColumn(name="MOIM_ID")
	private Moim moim;
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	private People people;
	
	@Column(name="PLAN_LIST")
	private String plan_list;
	
	@Column(name="CHECK_CONFIRM")	
	private char checkConfirm;
	
}
