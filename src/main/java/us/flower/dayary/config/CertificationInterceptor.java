package us.flower.dayary.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import us.flower.dayary.domain.People;
/**
 * 인터셉터 설정 2019-09-23
 *   by 최성준
 */
@Component
public class CertificationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 세션 객체 생성
		HttpSession session = request.getSession();

		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("!@#$%^&*()!@#$%^&*(!@#$%^&*" + path);
		// 세션이 없으면(로그인되지 않은 상태)
		if (session.getAttribute("peopleEmail") == null) {

				session.setAttribute("savePage", path);// 로그인 후 가야할 요청 페이지 저장.
	
				// login 페이지로 이동
				response.sendRedirect(request.getContextPath() + "/people/signinWarning");

			return false; // 메인 액션으로 가지 않음
		} else {

			return true; // 메인 액션으로 이동
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}