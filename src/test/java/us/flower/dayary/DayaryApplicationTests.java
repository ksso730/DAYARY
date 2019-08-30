package us.flower.dayary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.common.BCRYPT;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.MoimPeopleRepository;
import us.flower.dayary.repository.MoimRepository;
import us.flower.dayary.repository.PeopleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayaryApplicationTests {

	@Autowired
	MoimPeopleRepository moimPeopleRepository;
	@Autowired
	MoimRepository moimRepository;
	@Autowired
	PeopleRepository peopleRepository;
	@Autowired
	BCRYPT bcrypt;
	
	/*Testcase 작성 insert문 후 select*/
    @Test
    public void createMoimAndInsertPeople()
	{

//		People a = People.builder().id("a").password(bcrypt.hashpw("1234")).name("가").photo("awegwef").close(false).build();
//		People b = People.builder().id("b").password(bcrypt.hashpw("1234")).name("나").photo("awegawef").close(false).build();
//		People c = People.builder().id("c").password(bcrypt.hashpw("1234")).name("다").photo("awegawef").close(false).build();
//		peopleRepository.save(a);
//		peopleRepository.save(b);
//		peopleRepository.save(c);
//
//		Moim m = Moim.builder().categoryNo(1).title("JPA스터디").intro("모여라~").peopleLimit(10).build();
//		moimRepository.save(m);
//
//		MoimPeople m1 =MoimPeople.builder().moim(Moim.builder().no(4).build()).peopleNo(1).build();
//		MoimPeople m2 =MoimPeople.builder().moim(Moim.builder().no(4).build()).peopleNo(2).build();
//		MoimPeople m3 =MoimPeople.builder().moim(Moim.builder().no(4).build()).peopleNo(3).build();
//		moimPeopleRepository.save(m1);
//		moimPeopleRepository.save(m2);
//		moimPeopleRepository.save(m3);
	}
    
    @Test
	@Transactional
    public void selectMoim() throws JsonProcessingException {
		Optional<Moim> moim = moimRepository.findById((long) 4);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		ObjectMapper mapper = new ObjectMapper();
		String json4PrettyString = mapper.writeValueAsString(moim.get());//출력이쁘게
		System.out.println(json4PrettyString);



		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }
}
