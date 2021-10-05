package com.bookpartner.domain.adminpartner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "ADMIN_PARTNER")
public class AdminPartner {

    @Id
    @Column(name = "ADM_ACCOUNT")
    private String admAccount;
    @Column(name = "ADM_PASSWD")
    private String admPasswd;
    @Column(name = "ADM_NAME")
    private String admName;
    @Column(name = "ADM_JOINS_ID")
    private String admJoinsId;
    @Column(name = "ADM_BRANCH")
    private String admBranch;
    @Column(name = "ADM_GRADE")
    private String admGrade;
    @Column(name = "ADM_LAST")
    private String admLast;
    @Column(name = "ADM_TOUCH")
    private String admTouch;
    @Column(name = "ADM_STATUS")
    private String admStatus;
    @Column(name = "ADM_MEMO")
    private String admMemo;
    @Column(name = "ADM_GRADE2")
    private String admGrade2;
    @Column(name = "ADM_GRADE3")
    private String admGrade3;
    @Column(name = "ADM_GRADE4")
    private String admGrade4;
    @Column(name = "ADM_TMP_PWD")
    private String admTmpPwd;
    @Column(name = "ADM_HP")
    private String admHp;
    @Column(name = "ADM_HP_AUTH")
    private String admHpAuth;
    @Column(name = "ADM_PASS_EXPIRE")
    private String admPassExpire;
    @Column(name = "ADM_ROLE")
    private String admRole;
    @Column(name = "ADM_PRIVACY_TREAT")
    private String admPrivacyTreat;
    @Column(name = "ADM_REG_DT")
    private java.sql.Date admRegDt;

    @Builder
    public AdminPartner(String admAccount, String admName, String admPasswd){
        this.admAccount = admAccount;
        this.admName = admName;
        this.admPasswd = admPasswd;
    }

}
