package us.flower.dayary.controller.moim.joinpeople;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.joinpeople.MoimJoinPeopleService;

@Controller
public class MoimJoinPeopleController {

    @Autowired
    private moimService moimService;
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
	@ResponseBody
	@PostMapping("/moimParticipant/{moimNo}")
	public Map<String, Object> moimParticipant(@PathVariable("moimNo") long moimNo,HttpSession session) {
		Long peopleId = (Long) session.getAttribute("peopleId");
	
		
	
		Map<String,Object> returnData = new HashMap<String,Object>();
	
		try {	
				moimService.moimParticipant(peopleId,moimNo);
				returnData.put("code","1");
				returnData.put("message","모임가입완료:)");
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
	@ResponseBody
	@DeleteMapping("/moimParticipant/deletejoinedPeople/{moimPeopleNo}")
	public Map<String, Object> moimdeletejoinedPeople(@PathVariable("moimPeopleNo") long moimPeopleNo,HttpSession session) {
		Long peopleId = (Long) session.getAttribute("peopleId");
	
		
		Map<String,Object> returnData = new HashMap<String,Object>();
	
		  try {
			  	moimjoinPeopleService.deleteMoimPeople(moimPeopleNo);
				returnData.put("code","1");
				returnData.put("message","모임탈퇴 완료:)");
		  }catch (Exception e) {
	            returnData.put("code", "E3290");
	            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
	      }
		return returnData;
	}
}
