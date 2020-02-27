package us.flower.dayary.rest.controller.Moim.MoimPeople;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import us.flower.dayary.security.JwtTokenProvider;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.joinpeople.MoimJoinPeopleService;

@RestController
public class RestMoimPeopleController {
	
	@Autowired
	moimService moimService;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	MoimJoinPeopleService moimjoinPeopleService;
	
	/**
	 * 일반회원 모임 참가 
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 */
	@PostMapping("/rest/moimParticipant/{moimNo}/{PeopleNo}")
	public JSONObject RestmoimParticipant(@PathVariable("moimNo") long moimNo,@PathVariable("PeopleNo") long PeopleNo) {
		
			JSONObject returnData = new JSONObject();
		
			char joinCondition='Y';//참가자 승인후 Y
		 
			try {	
					char maker='N';//만든사람여부
					moimService.moimParticipant(PeopleNo,moimNo,joinCondition,maker);
					String moimPeopleNo = moimService.findMoimPeopleNoOne(PeopleNo, moimNo);// 참여자단건 조회(모임피플넘버를 단건으로 가져와서 moimPeople_no에
					returnData.put("code","1");  
					returnData.put("message","모임가입완료:)");
					returnData.put("moimPeopleNo",moimPeopleNo);
			}catch(Exception e) {
				    returnData.put("code", "E3290");
		            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
			}
			return returnData;
	}
	/**
	 * 일반회원 모임 탈퇴 
	 *
	 * @param locale
	 * @param moimPeopleNo
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @Date 2019-09-24
	 */
	@DeleteMapping("/rest/moimParticipant/deletejoinedPeople/{moimPeopleNo}")
	public JSONObject RestmoimdeletejoinedPeople(@PathVariable("moimPeopleNo") String moimPeopleNo) {
		long moimPeopleNoL = Long.parseLong(moimPeopleNo);
		JSONObject returnData = new JSONObject();
		  try {
			  	moimjoinPeopleService.deleteMoimPeople(moimPeopleNoL);
				returnData.put("code","1");
				returnData.put("message","모임탈퇴 완료:)");
		  }catch (Exception e) {
	            returnData.put("code", "E3290");
	            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
	      }
		return returnData;
	}
}
