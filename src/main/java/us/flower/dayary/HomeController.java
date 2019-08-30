package us.flower.dayary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.repository.MoimRepository;

import java.util.Optional;

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
}
