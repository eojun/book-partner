package com.bookpartner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalesSumDailyDto {

    private int pay_price_order;
    private int pay_price_cancel;
    private int pay_price_virtual;
    private int pay_price_virtual_count;
    private int pay_order_count;
    private int pay_cancel_count;
    private String pay_auth_date;

    public SalesSumDailyDto( String pay_auth_date, Integer pay_price_order, Integer pay_price_cancel, Long pay_order_count, Long pay_cancel_count, Integer pay_price_virtual, Long pay_price_virtual_count){
        this.pay_price_order = pay_price_order;
        this.pay_price_cancel = pay_price_cancel;
        this.pay_price_virtual_count = pay_price_virtual_count.intValue();
        this.pay_cancel_count = pay_cancel_count.intValue();
        this.pay_order_count = pay_order_count.intValue();
        this.pay_auth_date = pay_auth_date;
        if(pay_price_virtual != null)
            this.pay_price_virtual = pay_price_virtual;
        else
            this.pay_price_virtual = 0;
    }
}
