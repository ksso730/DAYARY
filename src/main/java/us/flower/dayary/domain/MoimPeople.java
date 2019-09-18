package us.flower.dayary.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 온라인모임참가자
 * by choiseongjun
 */
@Entity
@Table(name = "MOIM_PEOPLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoimPeople {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no")
	private long no;
	@ManyToOne
	@JoinColumn(name = "MOIM_NO")
	private Moim moim;
	@ManyToOne
	@JoinColumn(name = "PEOPLE_ID")
	private People people;
	@CreatedDate
	private LocalDateTime createdDate;

}
