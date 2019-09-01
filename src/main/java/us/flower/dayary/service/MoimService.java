package us.flower.dayary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import us.flower.dayary.domain.Category;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.CategoryRepository;
import us.flower.dayary.repository.MoimRepository;
import us.flower.dayary.repository.PeopleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {
	
	  private final PeopleRepository peopleRepository;
	  private final MoimRepository moimRepository;
	  private final CategoryRepository categoryRepository;

	  public void saveMoim(String id, String subject, Moim moim) {

		  People people = peopleRepository.findById(id);
	      Category category = categoryRepository.findBySubject(subject);
	      
	      moim.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
	      moim.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
	      
	      Moim moimData = new Moim(moim.getNo(), moim.getTitle(), moim.getIntro(),
	    		                                               moim.getPeopleLimit(), moim.getCreateDate(),  
	    		                                               moim.getUpdateDate(), people, category);
	      moimRepository.save(moimData);
    } 

	public List<Moim> findMoim(Moim moim) {

		return moimRepository.findAll();
	}

	public Optional<Moim> findMoimone(long no) {
		return moimRepository.findById(no);
	}

	
	
	

}
