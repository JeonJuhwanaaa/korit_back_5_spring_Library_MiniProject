package com.study.library.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 매개변수 http 안에 csrf / authorizeRequests / ... 넣는 작업
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/server/**", "/auth/**") // server로 시작하는 주소 , auth로 시작하는 주소
                .permitAll()                                      // 전부 허용해줘라
                .anyRequest()                           // 어떤 요청도
                .authenticated();                       // 인증 거쳐라
    }
}
