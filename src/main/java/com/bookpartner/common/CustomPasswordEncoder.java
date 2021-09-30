package com.bookpartner.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * custom 암호화 적용
 */
@Component(value="customPasswordEncoder")
public class CustomPasswordEncoder {
	private static final Logger logger = LoggerFactory.getLogger(CustomPasswordEncoder.class);

	/**
	 * DB에서 가져온 암호화된 패스워드와 이용자가 직접 입력한 패스워드를 비교해 준다.
	 */

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
    	logger.debug("[CustomPasswordEncoder] encPass="+encPass+" / rawPass="+rawPass+" / salt="+salt);
    	
    	boolean result = false;    	

    	try {
				String encodeStr = SHA256Util.getMD5HashAndSHA256Password(rawPass);
    		
    		if(encPass.equals(encodeStr)){
    			result = true;
    		}
    		
    	} catch(Exception e) {
    		logger.debug("isPasswordValid error.....");
    	}

		logger.debug("isPasswordValid = ");
        return result;
    }
    
}
