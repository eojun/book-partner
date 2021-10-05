package com.bookpartner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalesSumDto {

    private int pay_price_order;
    private int pay_price_cancel;
    private int pay_price_virtual;
    private int pay_price_virtual_count;
    private int order_order_count;
    private int order_cancel_count;

    public SalesSumDto(int pay_price_order, int pay_price_cancel, int pay_price_virtual, int pay_price_virtual_count, int order_order_count, int order_cancel_count){
        this.pay_price_order = pay_price_order;
        this.pay_price_cancel = pay_price_cancel;
        this.pay_price_virtual = pay_price_virtual;
        this.pay_price_virtual_count = pay_price_virtual_count;
        this.order_order_count = order_order_count;
        this.order_cancel_count = order_cancel_count;
    }

}
