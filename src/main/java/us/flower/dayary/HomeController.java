package us.flower.dayary;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import us.flower.dayary.domain.People;
import us.flower.dayary.domain.VisitCheck;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.visitcheck.VisitCheckRepository;

@Controller
public class HomeController {
	@Autowired
	VisitCheckRepository visitcheckRepository;
	@Autowired
	MoimRepository moimRepository;
	
	
	@RequestMapping("/")
	public String Home() throws Exception {
//		Optional<Moim> moim = moimRepository.findById((long) 48);
//		return moim.get();
		VisitCheck vo=new VisitCheck();
		insertVisitor(vo);
		return "main";
	}
	//방문자체크 메서드
	public VisitCheck insertVisitor(VisitCheck vo) throws Exception{
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        vo.setVisit_ip(req.getRemoteAddr());
        vo.setVisit_agent(req.getHeader("User-Agent"));//브라우저 정보
        vo.setVisit_refer(req.getHeader("referer"));//접속 전 사이트 정보
        return visitcheckRepository.save(vo);
    }
	@GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
	@GetMapping("/loginSuccess")
	public String loginSuccess(People people,HttpSession session) {
		return "main";
	}

}
