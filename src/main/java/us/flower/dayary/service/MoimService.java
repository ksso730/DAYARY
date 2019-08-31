package us.flower.dayary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.repository.MoimRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {
	
	private final MoimRepository moimRepository;
	
	public boolean existsByNo(long moinNo) {
		
		boolean isExist = moimRepository.existsById(moinNo);
		if(isExist) {
			return true;
		}else {
			return false;
		}
	}
	
	public void saveMoim(Moim moim) {
		
		moimRepository.save(moim);
		 
	}

	public List<Moim> findMoim(Moim moim) {

		return moimRepository.findAll();
	}
	
	

}
