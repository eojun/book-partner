package com.bookpartner.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value="customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		
		String username = (String)authentication.getPrincipal();    
		String password = (String)authentication.getCredentials();
		
		logger.info("사용자가 입력한 로그인정보입니다. {}", username + "/" + password);
		
		UserDetailsImpl user;
		Collection<? extends GrantedAuthority> authorities;
				
		try {
			
			user = (UserDetailsImpl)userService.loadUserByUsername(username);
			
			if(passwordEncoder.isPasswordValid(user.getPassword(), password, "")){
				logger.info("정상 로그인입니다.");
				
				authorities = user.getAuthorities();
			} else {
				
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			}

		} catch(UsernameNotFoundException e) {
            logger.info(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            logger.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
        	e.printStackTrace();
            logger.info(e.toString());
            throw new RuntimeException(e.getMessage());
        }
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password, authorities);
		return result;
		
	}

}
