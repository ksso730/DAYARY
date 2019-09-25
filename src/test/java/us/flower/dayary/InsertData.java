package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Category;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.CategoryRepository;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.people.PeopleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertData {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	PeopleRepository peopleRepository;

	@Autowired
	CommunityBoardRepository communityBoardRepository;
	
	  @Test
	    public void createMoimAndInsertPeople()
		{
		  Category category=new Category();
		  category.setId(1L);
		  category.setSubject("영어");
		  categoryRepository.save(category);
		  category.setId(2L);
		  category.setSubject("수능");
		  categoryRepository.save(category);
		  category.setId(3L);
		  category.setSubject("공무원");
		  categoryRepository.save(category);
		  category.setId(4L);
		  category.setSubject("프로그래밍");
		  categoryRepository.save(category);
		  category.setId(5L);
		  category.setSubject("대기업");
		  categoryRepository.save(category);
		  category.setId(6L);
		  category.setSubject("공기업");
		  categoryRepository.save(category);
		}
	@Autowired
	BoardGroupRepository brdgrprepo;
	@Test
	public void insertTest() {
		BoardGroup brdgrp=new BoardGroup();
		brdgrp.setId(1L);
		brdgrp.setName("타임라인 ");
		brdgrprepo.save(brdgrp);

		BoardGroup brdgrp2=new BoardGroup();
		brdgrp2.setId(2L);
		brdgrp2.setName("자유게시판 ");
		brdgrprepo.save(brdgrp2);

		BoardGroup brdgrp3=new BoardGroup();
		brdgrp3.setId(3L);
		brdgrp3.setName("취업게시판 ");
		brdgrprepo.save(brdgrp3);

		BoardGroup brdgrp4=new BoardGroup();
		brdgrp4.setId(4L);
		brdgrp4.setName("자격증게시판 ");
		brdgrprepo.save(brdgrp4);

		BoardGroup brdgrp5=new BoardGroup();
		brdgrp5.setId(5L);
		brdgrp5.setName("어학게시판 ");
		brdgrprepo.save(brdgrp5);

		BoardGroup brdgrp6=new BoardGroup();
		brdgrp6.setId(6L);
		brdgrp6.setName("스터디룸 소개 ");
		brdgrprepo.save(brdgrp6);

		BoardGroup brdgrp7=new BoardGroup();
		brdgrp7.setId(7L);
		brdgrp7.setName("독서실소개 ");
		brdgrprepo.save(brdgrp7);

//		People defualtUser = new People();
//		defualtUser.setEmail("user");
//		defualtUser.setPassword("123");
		People defualtUser = peopleRepository.getOne(1L);

		for(int i=0; i<12; i++){
			CommunityBoard board = new CommunityBoard();
			board.setMemo("테스트");
			board.setTitle("테스트");
			board.setBoardGroup(brdgrp2);
			board.setHeart(3);
			board.setPeople(defualtUser);
			communityBoardRepository.save(board);
		}
	}
}
