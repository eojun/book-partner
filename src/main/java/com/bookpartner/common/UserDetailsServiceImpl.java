package com.bookpartner.common;

import com.bookpartner.domain.adminpartner.AdminPartner;
import com.bookpartner.service.AdminPartnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService { 
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AdminPartnerService adminPartnerService;

	@Override
	@Transactional
	public UserDetailsImpl loadUserByUsername(String loginid) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername("+loginid+")");
		 UserDetailsImpl user;
		try{

			
			
			logger.info("loadUserByUsername("+loginid+")");

			AdminPartner login = adminPartnerService.getAdminPartner(loginid);

			String username = loginid;

			user = new UserDetailsImpl();

			user.setUserName(username);
			user.setUserRealName(StringUtil.nvl(login.getAdmName()));
			user.setPassword(StringUtil.nvl(login.getAdmPasswd()));
			user.setAuthcd(login.getAdmRole() == null ? "ROLE_ADMIN" : login.getAdmRole());
			user.setJoinsId(StringUtil.nvl(login.getAdmJoinsId()));

		}catch(Exception e){
			logger.debug(e.getMessage());
			throw new UsernameNotFoundException("No user found named " + loginid );
		}

		return user;
	}

}
