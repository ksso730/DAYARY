package us.flower.dayary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 카테고리[온라인모임/오프라인모임]
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CATEGORY")
public class Category {

    @Id
    @Column(name="NO")
    private long no;

    @Column(name="SUBJECT")
    private String subject;
    
	/*
	 * Category.builder().no(1L).subject("운동").build();
	 * Category.builder().no(2L).subject("게임").build();
	 * Category.builder().no(3L).subject("외국어").build();
	 * Category.builder().no(4L).subject("음악").build();
	 * Category.builder().no(5L).subject("동물").build();
	 */
}
