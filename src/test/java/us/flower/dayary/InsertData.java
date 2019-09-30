//package us.flower.dayary;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import us.flower.dayary.domain.BoardGroup;
//import us.flower.dayary.domain.Common;
//import us.flower.dayary.domain.People;
//import us.flower.dayary.repository.BoardGroupRepository;
//import us.flower.dayary.repository.CommonRepository;
//import us.flower.dayary.repository.people.PeopleRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InsertData {
//
//	@Autowired
//	CommonRepository commonRepository;
//
//	@Autowired
//	PeopleRepository peopleRepository;
//
//	  @Test
//	  public void createMoimAndInsertPeople(){
//
//		  Common comm = new Common();
//
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("01");
//		  comm.setCommName("영어");
//		  commonRepository.save(comm);
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("02");
//		  comm.setCommName("수능");
//		  commonRepository.save(comm);
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("03");
//		  comm.setCommName("공무원");
//		  commonRepository.save(comm);
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("04");
//		  comm.setCommName("프로그래밍");
//		  commonRepository.save(comm);
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("05");
//		  comm.setCommName("대기업");
//		  commonRepository.save(comm);
//		  comm.setCommHead("CA1");
//		  comm.setCommCode("06");
//		  comm.setCommName("공기업");
//		  commonRepository.save(comm);
//		}
//	@Autowired
//	BoardGroupRepository brdgrprepo;
//	@Test
//	public void insertTest() {
//		BoardGroup brdgrp=new BoardGroup();
//		brdgrp.setId(1L);
//		brdgrp.setName("타임라인 ");
//		brdgrprepo.save(brdgrp);
//
//		BoardGroup brdgrp2=new BoardGroup();
//		brdgrp2.setId(2L);
//		brdgrp2.setName("자유게시판 ");
//		brdgrprepo.save(brdgrp2);
//
//		BoardGroup brdgrp3=new BoardGroup();
//		brdgrp3.setId(3L);
//		brdgrp3.setName("취업게시판 ");
//		brdgrprepo.save(brdgrp3);
//
//		BoardGroup brdgrp4=new BoardGroup();
//		brdgrp4.setId(4L);
//		brdgrp4.setName("자격증게시판 ");
//		brdgrprepo.save(brdgrp4);
//
//		BoardGroup brdgrp5=new BoardGroup();
//		brdgrp5.setId(5L);
//		brdgrp5.setName("어학게시판 ");
//		brdgrprepo.save(brdgrp5);
//
//		BoardGroup brdgrp6=new BoardGroup();
//		brdgrp6.setId(6L);
//		brdgrp6.setName("스터디룸 소개 ");
//		brdgrprepo.save(brdgrp6);
//
//		BoardGroup brdgrp7=new BoardGroup();
//		brdgrp7.setId(7L);
//		brdgrp7.setName("독서실소개 ");
//		brdgrprepo.save(brdgrp7);
//
//	}
//}
