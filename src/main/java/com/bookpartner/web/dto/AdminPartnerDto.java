package com.bookpartner.web.dto;

import com.bookpartner.domain.AdminPartner;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class AdminPartnerDto {
    private String admAccount;
    private String admPasswd;
    private String admName;
    private String admJoinsId;

    public AdminPartner toEntity() {
        return AdminPartner.builder()
                .admAccount(admAccount)
                .admName(admName)
                .admPasswd(admPasswd)
                .build();
    }

    @Builder
    public AdminPartnerDto(String admAccount, String admName, String admPasswd){
        this.admAccount = admAccount;
        this.admName = admName;
        this.admPasswd = admPasswd;
    }
}
