package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
