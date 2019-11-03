package us.flower.dayary.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Moim_Board")
public class MoimBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
    @ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	private People people;
    @ManyToOne
	@JoinColumn(name = "BOARD_GROUP_ID")
	private BoardGroup boardGroup;
    @ManyToOne
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
