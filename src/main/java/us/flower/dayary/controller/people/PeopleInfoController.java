package us.flower.dayary.controller.people;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import us.flower.dayary.domain.People;
import us.flower.dayary.repository.people.PeopleRepository;

@Controller
public class PeopleInfoController {

	@Autowired
	PeopleRepository peopleRepository;
	
	
	/**
	 * 내 정보 조회
	 *
	 * @param
	 * @return
	 * @throws @author choiseongjun
	 */
	@GetMapping("/myprofileView")
	public String myprofileView(HttpSession session,Model model) {
		long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다.
		
		peopleRepository.findById(peopleId).ifPresent(peopleinfo->model.addAttribute("peopleinfo",peopleinfo));;
		
		
		return "people/myprofile";
	}
}
