package us.flower.dayary.controller.moim.meetup;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.meetup.MoimMeetUpService;

@Controller
public class MoimMeetUpController {
	
	@Autowired
	MoimMeetUpService moimMeetupService;
	
	@ResponseBody
	@PostMapping("/moimoffMake/{no}")
	public Map<String, Object> moimoffMake(@PathVariable("no") long no,@Valid @RequestBody Meetup meetUp,HttpSession session) {
		  
			long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다
		
			
			
			
		  Map<String,Object> returnData = new HashMap<String,Object>();
		  
		   
		 try { 
			 	moimMeetupService.moimoffMake(meetUp,peopleId,no);
			 	returnData.put("code","1"); 
			 	returnData.put("message","정모 만들기 완료:)");
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 모임 오프라인모임 만들기 화면으로
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimoffMakeView/{no}")
	public String moimoffMakeView(@PathVariable("no") long no, Model model,Meetup meetUp) {

		model.addAttribute("no", no);
		return "moim/popup/moimoffMake";
	}
}
