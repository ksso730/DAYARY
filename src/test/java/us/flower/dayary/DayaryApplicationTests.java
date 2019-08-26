package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.repository.MoimPersonRepository;
import us.flower.dayary.repository.MoimRepository;
import us.flower.dayary.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayaryApplicationTests {

	@Autowired
	MoimPersonRepository moimPersonRepo;
	@Autowired
	MoimRepository moimRepo;
	@Autowired
	PersonRepository personRepo;
	
	/*Testcase 작성 insert문 후 select*/
    @Test
    public void createMoimAndInsertPeople() {
		Moim moim=new Moim();
    }
    
    @Test
    public void selectMoim() {
    }
}
