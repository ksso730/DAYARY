package us.flower.dayary.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener, HttpSessionAttributeListener {
	
	public static int count;
	 
    public static int getCount() {
        return count;
    }


  @Override
  public void sessionCreated(HttpSessionEvent event) {
	  HttpSession session = event.getSession(); //request에서 얻는 session과 동일한 객체
      session.setMaxInactiveInterval(60*20);
       
      count++;
       
      session.getServletContext().log(session.getId() + " 세션생성 " + ", 접속자수 : " + count);

      session.setAttribute("sessioncount", count);
    // 세션 생성시 호출
    System.out.println("[ session ] created / id : " + event.getSession().getId());
  }
 
  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    // 세션 소멸시 호출
    System.out.println("[ session ] destroyed / id : " + se.getSession().getId());
  }
 
  @Override
  public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
    // 세션ID 변경시 호출
    System.out.println("[ session ] changed / oldId : " + oldSessionId + " / newId : " + se.getSession().getId());
  }
 
  @Override
  public void attributeAdded(HttpSessionBindingEvent se) {
    // 프라퍼티 추가시 호출
    System.out.println("[ session ] add / key : " + se.getName() + ", value : " + se.getValue());
  }
 
  @Override
  public void attributeRemoved(HttpSessionBindingEvent se) {
    // 프라퍼티 삭제시 호출
    System.out.println("[ session ] remove / key : " + se.getName());
  }
 
  @Override
  public void attributeReplaced(HttpSessionBindingEvent se) {
    // 프라퍼티 값 변경시 호출
    System.out.println("[ session ] replace / key : " + se.getName() + ", value : " + se.getValue() + " --> " +  se.getSession().getAttribute(se.getName()));
  }
}