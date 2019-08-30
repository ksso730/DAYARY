package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.Moim;

@Controller
public class MoimController {
	
	@GetMapping("/moimlistView")
	public String moimListView() {
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
	 /**
		 * 모임 만들기 
		 * @param locale
		 * @param Moim
		 * @return
		 * @throws Exception
		 * @author choiseongjun
		 */
	@ResponseBody
	@PostMapping("/moimMake")
	public String moimMake(@RequestBody Moim moim) {
		System.out.println(moim.toString());
		return null;
	}
}
