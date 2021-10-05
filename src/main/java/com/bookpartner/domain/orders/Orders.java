package com.bookpartner.domain.orders;

import com.bookpartner.domain.ordergoods.OrderGoods;
import com.bookpartner.domain.payinfo.PayInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Orders {

    @Id
    @Column(name = "ORD_ORDER_ID")
    private String ordOrderId;
    @Column(name = "ORD_TYPE")
    private String ordType;
    @Column(name = "ORD_CUST_ID")
    private String ordCustId;
    @Column(name = "ORD_CUST_NAME")
    private String ordCustName;
    @Column(name = "ORD_CUST_TEL1")
    private String ordCustTel1;
    @Column(name = "ORD_CUST_TEL2")
    private String ordCustTel2;
    @Column(name = "ORD_EMAIL")
    private String ordEmail;
    @Column(name = "ORD_RCV_NAME")
    private String ordRcvName;
    @Column(name = "ORD_RCV_ZIP")
    private String ordRcvZip;
    @Column(name = "ORD_RCV_ADDR")
    private String ordRcvAddr;
    @Column(name = "ORD_RCV_TEL1")
    private String ordRcvTel1;
    @Column(name = "ORD_RCV_TEL2")
    private String ordRcvTel2;
    @Column(name = "ORD_COUNTRY_CODE")
    private String ordCountryCode;
    @Column(name = "ORD_DATE")
    private String ordDate;
    @Column(name = "ORD_TIME")
    private String ordTime;
    @Column(name = "ORD_PAY_METHOD")
    private String ordPayMethod;
    @Column(name = "ORD_GOOD_NAME")
    private String ordGoodName;
    @Column(name = "ORD_DELIVERY_DATE1")
    private String ordDeliveryDate1;
    @Column(name = "ORD_DELIVERY_DATE2")
    private String ordDeliveryDate2;
    @Column(name = "ORD_JOINS_ID")
    private String ordJoinsId;
    @Column(name = "ORD_PAY_DATE")
    private String ordPayDate;
    @Column(name = "ORD_CAN_DATE")
    private String ordCanDate;
    @Column(name = "ORD_EXPECT_DATE")
    private String ordExpectDate;
    @Column(name = "ORD_EXT_DATE")
    private String ordExtDate;
    @Column(name = "ORD_RECEIPT_YN")
    private String ordReceiptYn;
    @Column(name = "ORD_REQ_MSG")
    private String ordReqMsg;
    @Column(name = "ORD_PACKAGE_MSG")
    private String ordPackageMsg;
    @Column(name = "ORD_MEMO")
    private String ordMemo;
    @Column(name = "ORD_MOD_DATE")
    private Date ordModDate;
    @Column(name = "ORD_MOD_EMP")
    private String ordModEmp;
    @Column(name = "ORD_PASSWD")
    private String ordPasswd;
    @Column(name = "ORD_TRANS_CODE")
    private String ordTransCode;
    @Column(name = "ORD_MILEAGE_DATE")
    private String ordMileageDate;
    @Column(name = "ORD_AGENT")
    private String ordAgent;
    @Column(name = "ORD_SUB_AGENT")
    private String ordSubAgent;
    @Column(name = "ORD_BILLING_CODE")
    private String ordBillingCode;
    @Column(name = "ORD_BILLING_ID")
    private String ordBillingId;
    @Column(name = "ORD_BILLING_PG")
    private String ordBillingPg;
    @Column(name = "ORD_BILLING_CORP_NUM")
    private String ordBillingCorpNum;
    @Column(name = "ORD_BUNDLE_YN")
    private String ordBundleYn;
    @Column(name = "ORD_STATUS")
    private String ordStatus;

    @OneToMany(mappedBy = "orders")
    List<PayInfo> payinfos = new ArrayList<>();

    @OneToMany(mappedBy = "orders")
    List<OrderGoods> ordergoods = new ArrayList<>();

}
