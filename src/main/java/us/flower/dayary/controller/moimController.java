package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class moimController {

	
	@GetMapping("/moimlistView")
	public String moimListView() {
		return "moim/moimList";
	}
}
