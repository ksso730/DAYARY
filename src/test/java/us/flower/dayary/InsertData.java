package us.flower.dayary;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.repository.CommonRepository;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.moim.todo.ToDoWriteRepository;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.community.CommunityBoardService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertData {

	@Autowired
	CommonRepository commonRepository;

	@Autowired
	PeopleRepository peopleRepository;

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
	ToDoWriteRepository todoWriteRepository;
	@Test
	public void createTodoList() {
		
		
		Date fromdata =new Date(20200228);
		Date startdata =new Date(20200229);
		
		for(long i=1;i<5000;i++) {
			ToDoWrite todowrite = new ToDoWrite();
			Moim moim = new Moim();
			People people=new People();
			people.setId(31L);
			moim.setId(49L);
			todowrite.setId(i);
			todowrite.setMoim(moim);
			todowrite.setPeople(people);
			todowrite.setPlan_title("쿼리성능테스트");
			todowrite.setProgress(100L);
			todowrite.setStatus("End");
			todowrite.setProgress_done(10L);
			todowrite.setProgress_total(10L);
			todowrite.setFrom_date(fromdata);
			todowrite.setTo_date(startdata);
			todoWriteRepository.save(todowrite);
		}
		
		//todoWriteRepository.save(todowrite);
	}

    @Autowired
    CommunityBoardRepository communityBoardRepository;

    @Autowired
	CommunityBoardService communityBoardService;
//	@Test
//    @Transactional
//	public void insertTest() {
//	   communityBoardService.getCommunityReplyList(119L);
//	}
}
