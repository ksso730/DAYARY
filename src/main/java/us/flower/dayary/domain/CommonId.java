package us.flower.dayary.domain;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

/**
 * 공통코드
 *   by 김유나
 */

@Data
public class CommonId implements Serializable {
	
	@Column(name="COMM_HEAD")
    private String commHead;
	
	@Column(name="COMM_CODE")
    private String commCode;
	
	
}
