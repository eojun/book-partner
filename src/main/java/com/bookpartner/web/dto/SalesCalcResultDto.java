package com.bookpartner.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalesCalcResultDto {

    private String pay_auth_date;
    private int pay_price;
    private int pay_price_count;
    private int pay_can_price;
    private int pay_can_price_count;
    private int deposit_can;

    @Builder
    public SalesCalcResultDto(String pay_auth_date, Integer pay_price, Long pay_price_count, Integer pay_can_price, Long pay_can_price_count, Integer deposit_can){
        this.pay_auth_date = pay_auth_date;
        this.pay_price = pay_price;
        this.pay_price_count = pay_price_count.intValue();
        this.pay_can_price = pay_can_price;
        this.pay_can_price_count = pay_can_price_count.intValue();
        this.deposit_can = deposit_can;
    }

}
