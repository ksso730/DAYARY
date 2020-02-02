package us.flower.dayary.domain.DTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Data
public class TempData {

	private long no1;
	private long no2;
	private char data1;
	private String name;
	private long cnt;
}
/**
 * 일반회원 모임 강퇴(모임장) 임시로 데이터 담기위해서 넘었다
 * +++또한 모임승인 데이터 담기위함
 * @param locale
 * @param Moim
 * @return returnData
 * @throws Exception
 * @author choiseongjun
 */
//@ResponseBody
//@PostMapping("/moimParticipant/banjoinedPeople")