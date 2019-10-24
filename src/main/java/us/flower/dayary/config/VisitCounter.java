//package us.flower.dayary.config;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import us.flower.dayary.domain.VisitCheck;
//import us.flower.dayary.repository.visitcheck.VisitCheckRepository;
//
//public class VisitCounter implements HttpSessionListener {
//	
//	@Autowired
//	VisitCheckRepository visitcheckRepository;
//	
//    @Override
//    public void sessionCreated(HttpSessionEvent arg0){
//        HttpSession session = arg0.getSession();
//        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
//        //등록되어있는 빈을 사용할수 있도록 설정해준다
//        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        //request를 파라미터에 넣지 않고도 사용할수 있도록 설정
//        System.out.println("Test!@#!!@#!@!@#!@#!@#@!#");
//        System.out.println(req);
//        VisitCheck visitCountDAO = (VisitCheck)wac.getBean("VisitCheck");
//        VisitCheck vo = new VisitCheck();
//        vo.setVisit_ip(req.getRemoteAddr());
//        vo.setVisit_agent(req.getHeader("User-Agent"));//브라우저 정보
//        vo.setVisit_refer(req.getHeader("referer"));//접속 전 사이트 정보
//        visitcheckRepository.save(vo);
//    }
//    @Override
//    public void sessionDestroyed(HttpSessionEvent arg0){
//        //TODO Auto-generated method stub
//    }
//}