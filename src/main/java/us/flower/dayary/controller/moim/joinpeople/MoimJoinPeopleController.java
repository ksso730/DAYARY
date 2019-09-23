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

@Controller
public class MoimJoinPeopleController {

    @Autowired
    private moimService moimService;
    @Autowired
    MoimPeopleRepository moimPeopleRepository;
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
	
			
		moimService.moimParticipant(peopleId,moimNo);
		
		returnData.put("code","1");
		returnData.put("message","모임가입완료:)");
		return returnData;
	}
	@Transactional
	@DeleteMapping("/moimParticipant/deletejoinedPeople")
	public Map<String, Object> moimdeletejoinedPeople(HttpSession session) {
		Long peopleId = (Long) session.getAttribute("peopleId");
	
		
		System.out.println(peopleId);
		Map<String,Object> returnData = new HashMap<String,Object>();
	
			
		moimPeopleRepository.deleteById(peopleId);
		returnData.put("code","1");
		returnData.put("message","모임탈퇴 완료:)");
		return returnData;
	}
}
