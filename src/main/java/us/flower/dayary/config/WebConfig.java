package us.flower.dayary.config;

import org.springframework.beans.factory.annotation.Autowired;
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
}