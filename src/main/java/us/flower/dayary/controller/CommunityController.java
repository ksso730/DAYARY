package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

	
	@GetMapping("/community/communityList")
	public String CommunityView() {
		
		return "community/communityList";
	}
}
