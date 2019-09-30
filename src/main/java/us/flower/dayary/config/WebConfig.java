package us.flower.dayary.config;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 인터셉터 설정 2019-09-23
 *   by 최성준
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	 /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    CertificationInterceptor certificationInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(certificationInterceptor)
                .addPathPatterns("/moimlistView/moimdetailView/**")
                .excludePathPatterns("signinView");
    }
    
    /* the request was rejected because the url contained a potentially malicious string ";" 500에러 떄문에  추가했음 by choiseongjun 2019-09-29*/
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
               servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
               SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
               sessionCookieConfig.setHttpOnly(true);
            }
        }; 
    }

}
