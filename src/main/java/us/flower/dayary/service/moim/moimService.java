package us.flower.dayary.service.moim;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;

public interface moimService {

	Map<String, Object> getMoimCategory();// 모임 카테고리 목록 조회

	void saveMoim(String id, String subject, Moim moim, MultipartFile file);//모임 만들기

	Optional<Moim> findMoimone(long no);//모임한개찾기



	byte[] getMoimImage(String imageName) throws Exception;//모임이미지들고오기

	MoimPeople moimParticipant(long peopleNo, long moimNo, char joinCondition);//회원 모임참가하기

	void deleteMoimOne(long moimNo);

	String findPeopleOne(Long people_no);//모임탈퇴조건으로 인해 한명조회

	String findMoimPeopleNoOne(long peopleId, long no);//모임넘버 조회해오는것(모임탈퇴할떄 Moim_people no기준으로 삭제)

	Page<Moim> selectListAll(Pageable pageable);

	List<Moim> findByTitle(String name);

	Page<Moim> selecttitleList(Pageable pageable, String title, Common common, String sido_code, String sigoon_code);

	void updateMoim(String id, String subject, Moim moim, MultipartFile file);



}
