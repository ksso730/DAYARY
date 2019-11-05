package us.flower.dayary.domain;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name="MOIM_TODO_WRITE_LIST")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDoWriteList extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOIM_ID")
	@JsonIgnore
	private Moim moim;
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	@JsonIgnore
	private People people;
	
	@ManyToOne
	@JoinColumn(name="MOIM_TODO_WRITE_ID")
	private ToDoWrite toDoWrite;
	
	@Column(name="PLAN_LIST")
	private String plan_list;
	
	@Column(name="CHECK_CONFIRM")
	private char checkConfirm;
	
	@Column(name="Detail")
	private char detail;
	
	@OneToMany(fetch = FetchType.LAZY,orphanRemoval=true,mappedBy = "toDoWriteList")
	private List<MoimBoard> moimboard; 
	
}
