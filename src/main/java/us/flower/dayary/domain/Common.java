package us.flower.dayary.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 공통코드
 *   by 김유나
 */

@Entity
@Data
@Table(name="COMMON")
public class Common{
	
	@Column(name="COMM_HEAD")
    private String commHead;
	
	@Id
	@Column(name="COMM_CODE")
    private String commCode;
	
	@Column(name="COMM_NAME")
    private String commName;
	
	@Column(name="SORT")
    private int sort;
	
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "COMMON_COMM_CODE", referencedColumnName =
	 * "PAR_COMM_CODE") private Common parent;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent") private List<Common>
	 * children;
	 */
	
}
