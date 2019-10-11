package us.flower.dayary.controller.moim.meetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.MeetupPeople;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.DTO.TempData;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.meetup.MeetupPeopleRepository;
import us.flower.dayary.repository.moim.meetup.MoimMeetUpRepository;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.moim.meetup.MoimMeetUpService;

@Controller
public class MoimMeetUpController {
	
	@Autowired
	MoimMeetUpService moimMeetupService;
	@Autowired
	MeetupPeopleRepository meetuppeopleRepository;
	@Autowired
	MoimMeetUpRepository moimmeetupRepository;
	@Autowired
	MoimRepository moimRepository;
	/**
	 * 오프라인모임 참여자 조회
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-11
	 */
    
	@ResponseBody
	@PostMapping("/meetuppeopleList")
	public Map<String, Object> meetuppeopleList(@Valid @RequestBody TempData tempdata,HttpSession session) {
	
		System.out.println(tempdata.getNo1());
		System.out.println(tempdata.getNo2());
		
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		Map<String,Object> returnData = new HashMap<String,Object>();
		People people=new People();
		people.setId(peopleId);
	
		Moim moim=new Moim();
		moim.setId(tempdata.getNo2());
  
		Meetup meetup=new Meetup();
		meetup.setId(tempdata.getNo1());//오프모임키
		
  
		
		   
			
			 
		 try {
			 		List<MeetupPeople> meetuppeoplelist=meetuppeopleRepository.findByMeetup_id(tempdata.getNo1());//오프라인모임키로 내부 사람들을 조회한다.

			 		System.out.println(meetuppeoplelist.toString());
				 	returnData.put("code","1"); 
				 	returnData.put("message","참여자 보기 조회:)");
			
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 오프라인모임 참여자 조회
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-11
	 */
    
	@ResponseBody
	@PostMapping("/moimmeetupPeopleList")
	public Map<String, Object> moimmeetupPeopleList(@Valid @RequestBody TempData tempdata,HttpSession session) {
	
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		Map<String,Object> returnData = new HashMap<String,Object>();
		People people=new People();
		people.setId(peopleId);
	
		Moim moim=new Moim();
		moim.setId(tempdata.getNo2());
  
		Meetup meetup=new Meetup();
		meetup.setId(tempdata.getNo1());//오프모임키
		
  
		
		   
			
			 
		 try {
			 		//List<MeetupPeople> meetuppeoplelist=meetuppeopleRepository.findByMeetup_idAndPeople_id(tempdata.getNo1(),peopleId);//오프라인모임참가취소하기위해서 현재접속중인아이디와 오프라인모임넘버를 찾는다.

			 		
				 	returnData.put("code","1"); 
				 	returnData.put("message","참여자 보기 조회:)");
			
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 일반회원 오프라인모임 참여
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-11
	 */
   
	@Transactional
	@ResponseBody
	@PostMapping("/moimmeetupJoin")
	public Map<String, Object> moimgrantjoinPeople(@Valid @RequestBody TempData tempdata,HttpSession session) {
	
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		Map<String,Object> returnData = new HashMap<String,Object>();
		People people=new People();
		people.setId(peopleId);
	
		Moim moim=new Moim();
		moim.setId(tempdata.getNo2());
  
		Meetup meetup=new Meetup();
		meetup.setId(tempdata.getNo1());//오프모임키
		
  
		MeetupPeople meetupPeople=new MeetupPeople();
		meetupPeople.setMoim(moim);
		meetupPeople.setPeople(people);
		meetupPeople.setMeetup(meetup);
		meetupPeople.setJoinCancel('N');
		   
			
			 
		 try {
			 meetuppeopleRepository.save(meetupPeople);
			 		//moimpeopleRepository.updateMoimPeoplejoinCondition(people,moim);

				 	returnData.put("code","1"); 
				 	returnData.put("message","참여 완료:)");
			 
			
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 오프라인 모임 생성
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-07
	 */
	@ResponseBody
	@PostMapping("/moimoffMake/{no}")
	public Map<String, Object> moimoffMake(@PathVariable("no") long no,@Valid @RequestBody Meetup meetUp,HttpSession session) {
		  
			long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		
			
			
			
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
	/**
	 * 모임 오프라인모임 디테일 화면으로
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimlistView/moimdetailView/moimmeetupDetail/{no}")
	public String moimmeetupDetail(@PathVariable("no") long no,Model model,Meetup meetUp,Sort sort) {
		   
		sort = sort.and(new Sort(Sort.Direction.DESC, "id"));
		List<Meetup> meetupList=moimmeetupRepository.findByMoim_id(no,sort);//오프라인 모임 내림차순정렬로 가져옴
			
		
		model.addAttribute("meetupList",meetupList);
		model.addAttribute("no",no);
		return "moim/meetup/moimmeetupDetail";
	}
}
