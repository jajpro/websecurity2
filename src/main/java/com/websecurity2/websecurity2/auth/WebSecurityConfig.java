package com.websecurity2.websecurity2.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .headers().frameOptions().disable();
        http.authorizeRequests()
                //페이지 권한 설정
                .antMatchers("/", "/members/new").permitAll()
                //home.html, 회원가입 리소스에 접근할 때( / , /members/new ,/hello )를 제외하고 모든 사용자 요청은 인증을 받도록 설정
                //즉, /,/members/new, /hello 가 아닌 다른 리소스 접근 시 formLogin 화면 나타남
                //혹은 .antMatchers("/", "/members/new").anonymous() 도 가능할 듯
                //.antMatchers(HttpMethod.POST,"/*").permitAll() ???
                .anyRequest().authenticated()
                .and()  //로그인 설정
                .formLogin()
                .and()  //로그아웃 설정
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .httpBasic();

    }
}
