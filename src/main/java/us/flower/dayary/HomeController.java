package us.flower.dayary;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	} 
}
