package us.flower.dayary.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import us.flower.dayary.domain.People;
import us.flower.dayary.repository.people.PeopleRepository;
 
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
	@Autowired
	PeopleRepository peopleRepository;
	
    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
        Authentication authentication) throws ServletException, IOException {
        
    	
    	//((SecurityMember)authentication.getPrincipal()).setIp(getClientIp(request));
    	
        HttpSession session = request.getSession();
        System.out.println("SDFSDfsdfsdfsdfsdfsd");
        String username = authentication.getName();
		People dbPeople = peopleRepository.findByName(username);
		session.setAttribute("peopleId", dbPeople.getId());// NO세션저장
		session.setAttribute("peopleName", dbPeople.getName());// 이름세션저장
		session.setAttribute("peopleEmail", dbPeople.getEmail());// ID세션저장
		session.setAttribute("people",dbPeople);
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
    
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("WL-Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_CLIENT_IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getRemoteAddr();
         }
         return ip;
    }
}


