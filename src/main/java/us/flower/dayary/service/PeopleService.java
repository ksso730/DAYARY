package us.flower.dayary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import us.flower.dayary.repository.PeopleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PeopleService {
	
	private final PeopleRepository peopleRepository;
	
	public void test() { 
		 
	}

}
