package com.bookpartner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalesRankDto {
    private String org_goods_name;
    private String org_busi_name;
    private String org_goods_id;
    private int org_ord_nums;
    private int org_sale_price;
    private int totalprice;


    public SalesRankDto(String org_goods_name, String org_busi_name, String org_goods_id, Integer org_ord_nums, Integer totalprice){
        this.org_goods_name = org_goods_name;
        this.org_busi_name = org_busi_name;
        this.org_goods_id = org_goods_id;
        this.org_ord_nums = org_ord_nums;
        this.totalprice = totalprice;
    }

}
