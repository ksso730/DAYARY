package us.flower.dayary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.service.MoimService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MoimController {
	
	private final MoimService moimService;
	
	 /**
	    * 모임 만들기 
	    * @param locale
	    * @param Moim
	    * @return
	    * @throws Exception
	    * @author yuna
	    */
	   @ResponseBody
	   @PostMapping("/moimMake")
	   public Map<String,Object> moimMake(@RequestBody Moim moim, HttpSession session) {
	      
	      Map<String,Object> returnData = new HashMap<String,Object>();
	      Long se_id = (Long) session.getAttribute("peopleNo");
	      String id = se_id.toString();

	      if(moim.getTitle().equals(null) || moim.getTitle().equals("")) {
	    	  returnData.put("code","0");
		      returnData.put("message","모임 이름을 입력해주세요");
	    	  return returnData;
	      }else if(moim.getPeopleLimit() == 0) {
	    	  returnData.put("code","0");
		      returnData.put("message","인원수를 입력해주세요");
	    	  return returnData;
	      }else if(moim.getCategoryNo() == 0) {
	    	  returnData.put("code","0");
		      returnData.put("message","모임 주제를 선택해주세요");
	    	  return returnData;
	      }
	      
	      try {
	         moimService.saveMoim(id, moim);
	         returnData.put("code","1");
	         returnData.put("message","저장되었습니다");
	         
	      }catch (Exception e) {
	         returnData.put("code","E3290");
	         returnData.put("message","데이터 확인 후 다시 시도해주세요.");
	      }
	      return returnData;
	   }

	
	 /**
		 * 모임 리스트 출력  
		 * @param locale
		 * @param Moim
		 * @return
		 * @throws Exception
		 * @author choiseongjun
		 */
	@GetMapping("/moimlistView")
	public String moimListView(Model model) {
		Moim moim=new Moim();
		List<Moim> moimList=moimService.findMoim(moim);
		System.out.println(moimList.toString());
		model.addAttribute("moimList",moimList);
		return "moim/moimList";
	}
	@GetMapping("/moimdetailView")
	public String moimDetailView() {
		return "moim/moimDetail";
	}
	@GetMapping("/moimMakeView")
	public String moimMakeView() {
		return "moim/moimMake";
	}
	
	
}
