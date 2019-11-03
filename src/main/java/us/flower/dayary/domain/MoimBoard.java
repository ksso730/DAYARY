package us.flower.dayary.domain;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import us.flower.dayary.domain.common.DateAudit;

@Entity
@Table(name = "Moim_Board")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoimBoard extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEOPLE_ID")
	private People people;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOARD_GROUP_ID")
	private BoardGroup boardGroup;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOIM_ID")
	private Moim moim;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "MEMO")
	private String memo;
	@Column(name = "CREATE_DATE", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	@Column(name = "UPDATE_DATE", updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_date;
	@Column(name = "DELETE_FLAG")
	private char delete_flag;
	@Column(name = "HEART")
	private long heart;
}
