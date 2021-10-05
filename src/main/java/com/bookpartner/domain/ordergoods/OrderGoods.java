package com.bookpartner.domain.ordergoods;

import com.bookpartner.domain.orders.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "ORDER_GOODS")
public class OrderGoods {

    @EmbeddedId // 복합키
    private OrderGoodsPK orderGoodsPK;
    @Column(name = "ORG_BAS_GOODS_ID")
    private String orgBasGoodsId;
    @Column(name = "ORG_SET_TYPE")
    private String orgSetType;
    @Column(name = "ORG_CUST_ID")
    private String orgCustId;
    @Column(name = "ORG_GOP_NO")
    private int orgGopNo;
    @Column(name = "ORG_BARCODE")
    private String orgBarcode;
    @Column(name = "ORG_LIBRO_CODE")
    private String orgLibroCode;
    @Column(name = "ORG_GOODS_NAME")
    private String orgGoodsName;
    @Column(name = "ORG_BUSI_NAME")
    private String orgBusiName;
    @Column(name = "ORG_BUSI_CODE")
    private String orgBusiCode;
    @Column(name = "ORG_PRICE")
    private int orgPrice;
    @Column(name = "ORG_ORD_NUMS")
    private int orgOrdNums;
    @Column(name = "ORG_SALE_PRICE")
    private int orgSalePrice;
    @Column(name = "ORG_DOWN")
    private int orgDown;
    @Column(name = "ORG_TOTAL_PRICE")
    private int orgTotalPrice;
    @Column(name = "ORG_SUP_PRICE")
    private int orgSupPrice;
    @Column(name = "ORG_EXP_DELV")
    private String orgExpDelv;
    @Column(name = "ORG_STATE")
    private String orgState;
    @Column(name = "ORG_CANCEL_NUMS")
    private int orgCancelNums;
    @Column(name = "ORG_CANCEL_DATE")
    private String orgCancelDate;
    @Column(name = "ORG_CANCEL_TIME")
    private String orgCancelTime;
    @Column(name = "ORG_ORD_DATE")
    private String orgOrdDate;
    @Column(name = "ORG_GIVEN_MILEAGE")
    private int orgGivenMileage;
    @Column(name = "ORG_STOCK_KIND")
    private String orgStockKind;
    @Column(name = "ORG_FREE_FGT")
    private String orgFreeFgt;
    @Column(name = "ORG_SENDER_CODE")
    private String orgSenderCode;
    @Column(name = "ORG_PRE_NUMS")
    private int orgPreNums;
    @Column(name = "ORG_OUT_NUMS")
    private int orgOutNums;
    @Column(name = "ORG_OUT_DATE")
    private String orgOutDate;
    @Column(name = "ORG_RET_NUMS")
    private int orgRetNums;
    @Column(name = "ORG_RET_CHK_NUMS")
    private int orgRetChkNums;
    @Column(name = "ORG_MOD_EMP")
    private String orgModEmp;
    @Column(name = "ORG_MOD_DATE")
    private java.sql.Date orgModDate;
    @Column(name = "ORG_ORI_NUMS")
    private int orgOriNums;
    @Column(name = "ORG_SORT_NO")
    private String orgSortNo;
    @Column(name = "ORG_BALJU_DATE")
    private String orgBaljuDate;
    @Column(name = "ORG_ACC_YN")
    private String orgAccYn;
    @Column(name = "ORG_EV_INFO")
    private String orgEvInfo;
    @Column(name = "ORG_AGE_CODE")
    private String orgAgeCode;
    @Column(name = "ORG_MATCH_DATE")
    private String orgMatchDate;
    @Column(name = "ORG_OPTION_NAME")
    private String orgOptionName;
    @Column(name = "ORG_OPTION_PRICE")
    private int orgOptionPrice;
    @Column(name = "ORG_SKY_PARAMS")
    private String orgSkyParams;
    @Column(name = "ORG_PARAM4")
    private String orgParam4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORG_ORDER_ID", referencedColumnName = "ORD_ORDER_ID", insertable = false, updatable = false)
    private Orders orders;

}
