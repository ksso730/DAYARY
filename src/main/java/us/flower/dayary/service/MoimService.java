package us.flower.dayary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.MoimRepository;
import us.flower.dayary.repository.PeopleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {
	
	  private final PeopleRepository peopleRepository;
	  private final MoimRepository moimRepository;

	
	public boolean existsByNo(long moinNo) {
		
		boolean isExist = moimRepository.existsById(moinNo);
		if(isExist) {
			return true;
		}else {
			return false;
		}
	}
	
	   
	   public void saveMoim(String id, Moim moim) {
	      People people = peopleRepository.findById(id);
	      System.out.println("people.getName" + people.getName());
	      System.out.println("people.getNo()" + people.getNo());
	      moim.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
	      moim.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
	      moim.setNo(1L);
	      System.out.println("moim.getNo()" + moim.getNo());
	      Moim moimData = new Moim(moim.getNo(), moim.getCategoryNo(), moim.getTitle(), moim.getIntro(), 
	                                                         moim.getPeopleLimit(), moim.getCreateDate(),  moim.getUpdateDate(), people);
	      moimRepository.save(moimData);
	      System.out.println("moim.getNo()" + moim.getNo());
	   }


	public List<Moim> findMoim(Moim moim) {

		return moimRepository.findAll();
	}
	
	

}
