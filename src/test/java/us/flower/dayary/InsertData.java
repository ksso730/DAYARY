package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Category;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertData {

	@Autowired
	CategoryRepository categoryRepository;
	
//	  @Test
//	    public void createMoimAndInsertPeople()
//		{
//		  Category category=new Category();
//		  category.setNo(0L);
//		  category.setSubject("수학");
//		  categoryRepository.save(category);
//		  category.setNo(1L);
//		  category.setSubject("영어");
//		  categoryRepository.save(category);
//		  category.setNo(2L);
//		  category.setSubject("수능");
//		  categoryRepository.save(category);
//		  category.setNo(3L);
//		  category.setSubject("공무원");
//		  categoryRepository.save(category);
//		  category.setNo(4L);
//		  category.setSubject("프로그래밍");
//		  categoryRepository.save(category);
//		  category.setNo(5L);
//		  category.setSubject("대기업");
//		  categoryRepository.save(category);
//		  category.setNo(6L);
//		  category.setSubject("공기업");
//		  categoryRepository.save(category);
//		}
	@Autowired
	BoardGroupRepository brdgrprepo;
	@Test
	public void insertTest() {
		BoardGroup brdgrp=new BoardGroup();
		brdgrp.setNo(1L);
		brdgrp.setName("타임라인 ");
		
		brdgrprepo.save(brdgrp);
	}
}
