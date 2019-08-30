package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

	
	@GetMapping("/signupView")
	public String signupView() {
		return "person/signup";
	}
}
