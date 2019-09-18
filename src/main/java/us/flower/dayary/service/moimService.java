package us.flower.dayary.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;

public interface moimService {

	Map<String, Object> getMoimCategory();// 모임 카테고리 목록 조회

	void saveMoim(String id, String subject, Moim moim, MultipartFile file);//모임 만들기

	Optional<Moim> findMoimone(long no);//모임한개찾기



	byte[] getMoimImage(String imageName) throws Exception;//모임이미지들고오기

	MoimPeople moimParticipant(long peopleNo, long moimNo);//회원 모임참가하기


}
