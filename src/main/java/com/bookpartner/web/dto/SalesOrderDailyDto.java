package com.bookpartner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalesOrderDailyDto {

    private String pay_auth_date;
    private String ord_order_id;
    private String ord_cust_name;
    private String ord_good_name;
    private int pay_price;
    private int pay_price_pure;
    private int pay_can_price;
    private int pay_price_virtual;
    private String ord_status_txt;

    public SalesOrderDailyDto(String pay_auth_date, String ord_order_id, String ord_cust_name, String ord_good_name, String ord_status_txt, Integer pay_price, Integer pay_can_price, Integer pay_price_virutal){
        this.pay_auth_date = pay_auth_date;
        this.ord_order_id = ord_order_id;
        this.ord_cust_name = ord_cust_name;
        this.ord_good_name = ord_good_name;
        this.ord_status_txt = ord_status_txt;
        this.pay_price = pay_price;
        this.pay_price_pure = pay_price + pay_can_price;
        this.pay_can_price = pay_can_price;
        this.pay_price_virtual = pay_price_virutal;
    }
}
