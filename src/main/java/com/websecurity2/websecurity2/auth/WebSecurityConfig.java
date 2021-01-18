package com.websecurity2.websecurity2.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                //.csrf().disable() //csrf() 적용 시 POST 로 보내는 모든 데이터는 히든 타입으로 form 에 넘기는 csrf 토큰 값이 필요.
                .headers().frameOptions().disable();

        http.authorizeRequests()
                //페이지권한설정
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.getValue())  //해당 리소스 ADMIN 만 접근가능
                .antMatchers("/members").hasAnyRole("ADMIN", "USER")    //hasRole 류는 "ROLE_" 를 자동 첨부
                .antMatchers("/members/new").anonymous()    //해당 리소스 로그인 되지 않은 사용자만 접근가능
                .antMatchers("/", "/api/**", "/swagger-ui.html").permitAll()   //모든 사용자 접근가능
                .anyRequest().authenticated()   //모든 요청에 대해, 인증된 사용자만 접근하도록 설정
                //로그인설정
                .and()
                .formLogin()
                //로그아웃설정
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                //403 예외처리
                .and()
                .exceptionHandling().accessDeniedPage("/error/denied");

    }
}
