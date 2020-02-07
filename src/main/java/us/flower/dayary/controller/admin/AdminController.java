package us.flower.dayary.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admini")
	public String admini() {
		return "admin/adminmain";
	}
	@GetMapping("/adminPeople")
	public String adminPeople() {
		return "admin/adminPeopleTableList";
	}
}
