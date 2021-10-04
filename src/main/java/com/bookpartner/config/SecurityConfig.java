package com.bookpartner.config;

import com.bookpartner.common.CustomAuthenticationProvider;
import com.bookpartner.service.AdminPartnerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // 인증 무시할 디렉토리 설정
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/static/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
            .formLogin() // 로그인 설정
                .loginPage("/adminPartner/login") // custom login page
                .defaultSuccessUrl("/") // 로그인 성공시 이동할 페이지
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/adminPartner/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // 세션 초기화
                .and()
            .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // 로그인 처리를 위한 AuthenticationManagerBuilder 설정
        auth.authenticationProvider(customAuthenticationProvider);
    }

}
