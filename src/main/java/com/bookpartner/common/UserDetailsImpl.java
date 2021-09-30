package com.bookpartner.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
	
	// 로그인 후 불변값만 저장
	private static final long serialVersionUID = 6674259262443808490L;
	
	private String userName;
	private String userRealName;
	private String password;
	private String authcd;
	private String joinsId;		// 파트너사 아이디, 파라미터 값으로 받는

	public static UserDetailsImpl getLoginUser()
    {
    	UserDetailsImpl user = null;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetailsImpl) {
    		if( ((UserDetailsImpl)principal).getAuthcd().compareTo("U1")  == 0 )
    		{
    			user = (UserDetailsImpl) principal;
    		}
    	}
    	return user;
    }
	
	public static UserDetailsImpl getLoginManager()
    {
    	UserDetailsImpl user = null;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetailsImpl) {
    		if( ((UserDetailsImpl)principal).getAuthcd().compareTo("U1")  != 0 )
    		{
    			user = (UserDetailsImpl) principal;
    		}
    	}
    	return user;
    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(authcd));
        
        return authorities;
    }


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	

}