package com.bookpartner.domain.payinfo;

import com.bookpartner.domain.orders.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "PAY_INFO")
public class PayInfo {

    @Id
    private String payNo;
    @Column(name = "PAY_CODE")
    private String payCode;
    @Column(name = "PAY_NAME")
    private String payName;
    @Column(name = "PAY_PRIMARY")
    private String payPrimary;
    @Column(name = "PAY_STATUS")
    private String payStatus;
    @Column(name = "PAY_ORD_PRICE")
    private int payOrdPrice;
    @Column(name = "PAY_PRICE")
    private int payPrice;
    @Column(name = "PAY_FEE")
    private int payFee;
    @Column(name = "PAY_CREATE_DATE")
    private String payCreateDate;
    @Column(name = "PAY_AUTH_DATE")
    private String payAuthDate;
    @Column(name = "PAY_AUTH_TIME")
    private String payAuthTime;
    @Column(name = "PAY_TID")
    private String payTid;
    @Column(name = "PAY_CB_CODE")
    private String payCbCode;
    @Column(name = "PAY_CB_NAME")
    private String payCbName;
    @Column(name = "PAY_IB_CODE")
    private String payIbCode;
    @Column(name = "PAY_INTEREST")
    private String payInterest;
    @Column(name = "PAY_QUOTA")
    private String payQuota;
    @Column(name = "PAY_AUTH_NO")
    private String payAuthNo;
    @Column(name = "PAY_RESULT_CODE")
    private String payResultCode;
    @Column(name = "PAY_MEMO")
    private String payMemo;
    @Column(name = "PARAM1")
    private String param1;
    @Column(name = "PARAM2")
    private String param2;
    @Column(name = "PARAM3")
    private String param3;
    @Column(name = "PARAM4")
    private String param4;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PAY_ORDER_ID", referencedColumnName = "ORD_ORDER_ID", insertable = false, updatable = false)
    private Orders orders;

}
