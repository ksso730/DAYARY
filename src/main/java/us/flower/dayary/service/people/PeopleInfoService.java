package us.flower.dayary.service.people;

import java.util.List;

import org.springframework.stereotype.Service;

import us.flower.dayary.domain.DTO.TempData;

public interface PeopleInfoService {

	byte[] getMoimImage(String imageName) throws Exception;

	void deletePeople(long peopleId);

	List<TempData> MyTodoProgress(long no);//나의 모임별 계획진행사항차트

}
