package com.bookpartner.domain.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "GOODS_ADD")
public class GoodsAdd {

    @Id
    @Column(name = "GOODS_ID")
    private String goodsId;
    @Column(name = "GOODS_ADD_ON")
    private String goodsAddOn;
    @Column(name = "GOODS_PAN_TYPE")
    private String goodsPanType;
    @Column(name = "GOODS_PAN_NUM")
    private String goodsPanNum;
    @Column(name = "GOODS_WIDTH")
    private String goodsWidth;
    @Column(name = "GOODS_DIMENSION")
    private String goodsDimension;
    @Column(name = "GOODS_HEIGHT")
    private String goodsHeight;
    @Column(name = "GOODS_WEIGHT")
    private String goodsWeight;
    @Column(name = "GOODS_PAGES")
    private String goodsPages;
    @Column(name = "GOODS_FROM")
    private String goodsFrom;
    @Column(name = "GOODS_LOCATION")
    private String goodsLocation;
    @Column(name = "GOODS_SAFE_NUM")
    private String goodsSafeNum;
    @Column(name = "GOODS_PRT_CHA")
    private String goodsPrtCha;
    @Column(name = "GOODS_PRT_DATE")
    private String goodsPrtDate;
    @Column(name = "GOODS_ABROAD")
    private String goodsAbroad;

}
