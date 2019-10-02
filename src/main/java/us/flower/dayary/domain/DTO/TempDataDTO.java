package us.flower.dayary.domain.DTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Data
public class TempDataDTO {

	private long no1;
	private long no2;
}
/**
 * 일반회원 모임 강퇴(모임장) 임시로 데이터 담기위해서 넘었다
 *
 * @param locale
 * @param Moim
 * @return returnData
 * @throws Exception
 * @author choiseongjun
 */
//@ResponseBody
//@PostMapping("/moimParticipant/banjoinedPeople")