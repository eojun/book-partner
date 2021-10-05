package com.bookpartner.service;

import com.bookpartner.domain.adminpartner.AdminPartner;
import com.bookpartner.domain.adminpartner.AdminPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminPartnerService implements UserDetailsService {
    private final AdminPartnerRepository adminPartnerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<AdminPartner> adminPartnerWrapper = adminPartnerRepository.findByAdmName(username);
        AdminPartner adminPartner = adminPartnerWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(username.equals("naverbook") || username.equals("daumshop")){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(adminPartner.getAdmName(),adminPartner.getAdmPasswd(), authorities);

    }

    @Transactional
    public AdminPartner getAdminPartner(String admAccount){
        AdminPartner adminPartner = adminPartnerRepository.findById(admAccount).get();

        return adminPartner;
    }




}
