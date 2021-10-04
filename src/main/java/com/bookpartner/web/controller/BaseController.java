package com.bookpartner.web.controller;

import com.bookpartner.common.UserDetailsImpl;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class BaseController {

    @GetMapping("/")
    public String index(){
        return "redirect:/sales/salesListDay";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/login";
    }


    /**
     * Description : 요청받은 모든 파라미터를 반환
     */
    protected CaseInsensitiveMap getParameterMap (Enumeration enumeration, Map map) {

        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        Map paramMap = null;
        HttpServletRequest request = sra.getRequest();

        paramMap = request.getParameterMap();

        CaseInsensitiveMap tempMap = new CaseInsensitiveMap();

        String key = "";
        String value = "";

        for(int i=0; i < paramMap.size() ; i++) {

            key = (String)enumeration.nextElement();

            int size = ((String[])paramMap.get(key)).length;

            value = ((String[])paramMap.get(key))[0];

            if(size > 1){
                tempMap.put(key, (String[])paramMap.get(key));
            }else{
                tempMap.put(key, value);
            }

        }

        tempMap.putAll(map);

        return tempMap;
    }
    /**
     * Description : 요청받은 모든 파라미터를 반환
     */
    protected CaseInsensitiveMap getParameterMap (Enumeration enumeration) {
        return getParameterMap(enumeration, new CaseInsensitiveMap());
    }
    /**
     *
     * Description : 요청받은 모든 파라미터를 반환
     */
    protected CaseInsensitiveMap getParameterMap() {

        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        HttpServletRequest req = sra.getRequest();

        Enumeration names = null;

        names = req.getParameterNames();

        return getParameterMap (names);

    }

    protected UserDetailsImpl getSecurityContextUser(){

        if(SecurityContextHolder.getContext() != null &&
                SecurityContextHolder.getContext().getAuthentication() != null){

            if( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl){
                UserDetailsImpl customUser = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                return customUser;
            }
        }

        return null;
    }

}
