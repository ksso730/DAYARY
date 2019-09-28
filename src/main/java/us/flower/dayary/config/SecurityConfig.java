
package us.flower.dayary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import us.flower.dayary.security.CustomAccessDeniedHandler;
import us.flower.dayary.security.CustomHttp403ForbiddenEntryPoint;
import us.flower.dayary.security.CustomUserDetailsService;
import us.flower.dayary.security.JwtAuthenticationEntryPoint;
import us.flower.dayary.security.JwtAuthenticationFilter;
/**
 * 시큐리티 설정 2019-09월초
 *   by 최성준
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)    /* the request was rejected because the url contained a potentially malicious string ";" 500에러 떄문에  추가했음 by choiseongjun 2019-09-29*/
				/*
				 * .and() .authorizeRequests() .antMatchers("/moimlistView/**")
				 * .access("ROLE_USER")
				 */
                	.and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll()
                    .antMatchers("/**/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/**/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                    .and()
                    .formLogin()
                       .loginPage("/signinView")
                       .permitAll()
                     .and()
                     	.logout().logoutUrl("/logout")
                        .logoutSuccessUrl("/signinView")
                        .invalidateHttpSession(true)
                        .and()
                        .exceptionHandling()
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                        .and().exceptionHandling().accessDeniedPage("/people/error");
                        /*.and()
                        .exceptionHandling().authenticationEntryPoint(new CustomHttp403ForbiddenEntryPoint());*/

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
