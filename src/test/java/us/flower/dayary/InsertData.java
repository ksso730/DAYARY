package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.service.community.CommunityBoardService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertData {
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


    @Autowired
    CommunityBoardRepository communityBoardRepository;

    @Autowired
	CommunityBoardService communityBoardService;
	@Test
    @Transactional
	public void insertTest() {
	   communityBoardService.getCommunityReplyList(119L);
	}
}
