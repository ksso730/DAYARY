package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.BoardGroup;
//import us.flower.dayary.domain.Category;
import us.flower.dayary.domain.BoardLike;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.BoardGroupRepository;
//import us.flower.dayary.repository.CategoryRepository;
import us.flower.dayary.repository.community.BoardLikeRepository;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertData {

//	@Autowired
//	CategoryRepository categoryRepository;

	@Autowired
	PeopleRepository peopleRepository;

	@Autowired
	CommunityBoardRepository communityBoardRepository;
	
	  @Test
	    public void createMoimAndInsertPeople()
		{
//		  Category category=new Category();
//		  category.setId(1L);
//		  category.setSubject("영어");
//		  categoryRepository.save(category);
//		  category.setId(2L);
//		  category.setSubject("수능");
//		  categoryRepository.save(category);
//		  category.setId(3L);
//		  category.setSubject("공무원");
//		  categoryRepository.save(category);
//		  category.setId(4L);
//		  category.setSubject("프로그래밍");
//		  categoryRepository.save(category);
//		  category.setId(5L);
//		  category.setSubject("대기업");
//		  categoryRepository.save(category);
//		  category.setId(6L);
//		  category.setSubject("공기업");
//		  categoryRepository.save(category);
		}
	@Autowired
	BoardGroupRepository brdgrprepo;

	@Autowired
	BoardLikeRepository boardLikeRepository;

	@Test
	public void insertTest() {

	}
}
