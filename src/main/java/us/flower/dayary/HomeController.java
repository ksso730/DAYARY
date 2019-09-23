package us.flower.dayary;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import us.flower.dayary.repository.moim.MoimRepository;

@Controller
public class HomeController {

	@Autowired
	MoimRepository moimRepository;

	@RequestMapping("/")
	public String Home() {
//		Optional<Moim> moim = moimRepository.findById((long) 48);
//		return moim.get();
		return "main";
	}
	
	 @GetMapping("/access-denied")
	    public String accessDenied() {
	        return "/error/access-denied";
	    }

}
