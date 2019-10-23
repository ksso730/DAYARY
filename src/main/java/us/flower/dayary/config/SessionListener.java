package us.flower.dayary.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener, HttpSessionAttributeListener {
   
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    // 세션 생성시 호출
    System.out.println("[ session ] created / id : " + se.getSession().getId());
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