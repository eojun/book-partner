package com.bookpartner.domain.searchindexgoods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "SEARCH_INDEX_GOODS")
public class SearchIndexGoods {

    @Id
    @Column(name = "GOODS_ID")
    private String goodsId;
    @Column(name = "MALL_ID")
    private String mallId;
    @Column(name = "BARCODE")
    private String barcode;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "GOODS_NAME")
    private String goodsName;
    @Column(name = "GOODS_SUB_NAME")
    private String goodsSubName;
    @Column(name = "COM_COMIC_ID")
    private String comComicId;
    @Column(name = "GOODS_DEGREE")
    private String goodsDegree;
    @Column(name = "EDITOR_RECOM_YN")
    private String editorRecomYn;
    @Column(name = "BESTSELLER_INFO")
    private String bestsellerInfo;
    @Column(name = "IMG_PATH1")
    private String imgPath1;
    @Column(name = "IMG_PATH2")
    private String imgPath2;
    @Column(name = "OPENBOOK")
    private String openbook;
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    @Column(name = "AUTHOR_NAME2")
    private String authorName2;
    @Column(name = "PUB_TYPE")
    private String pubType;
    @Column(name = "CAT_CODE")
    private String catCode;
    @Column(name = "CAT_CODE1")
    private String catCode1;
    @Column(name = "CAT_NAME1")
    private String catName1;
    @Column(name = "CAT_CODE2")
    private String catCode2;
    @Column(name = "CAT_NAME2")
    private String catName2;
    @Column(name = "CAT_CODE3")
    private String catCode3;
    @Column(name = "CAT_NAME3")
    private String catName3;
    @Column(name = "CAT_CODE4")
    private String catCode4;
    @Column(name = "CAT_NAME4")
    private String catName4;
    @Column(name = "PUB_NAME")
    private String pubName;
    @Column(name = "PUB_NAME2")
    private String pubName2;
    @Column(name = "PUB_DATE")
    private Date pubDate;
    @Column(name = "PRC_PRICE")
    private int prcPrice;
    @Column(name = "PRC_SALEPRICE")
    private int prcSaleprice;
    @Column(name = "PRC_DISCOUNT_RATE")
    private int prcDiscountRate;
    @Column(name = "PRC_GIVEN_MILEAGE")
    private int prcGivenMileage;
    @Column(name = "GOODS_SAIL_COUNT")
    private int goodsSailCount;
    @Column(name = "DELIVERY_LIMIT")
    private String deliveryLimit;
    @Column(name = "COUPON_AMT")
    private int couponAmt;
    @Column(name = "EVENT_NAME")
    private String eventName;
    @Column(name = "REVIEW_AVG")
    private String reviewAvg;
    @Column(name = "REVIEW_CNT")
    private String reviewCnt;
    @Column(name = "GOODS_STATUS")
    private String goodsStatus;
    @Column(name = "GOODS_STATUS_CODE")
    private String goodsStatusCode;
    @Column(name = "VISABLE_FLAG")
    private String visableFlag;
    @Column(name = "INTRO_EXIST")
    private String introExist;
    @Column(name = "AUTHOR_EXIST")
    private String authorExist;
    @Column(name = "INDEX_EXIST")
    private String indexExist;
    @Column(name = "TEXT_EXIST")
    private String textExist;
    @Column(name = "SERIES_EXIST")
    private String seriesExist;
    @Column(name = "GOODS_FROM")
    private String goodsFrom;
    @Column(name = "GOODS_UPDATE_DATE")
    private Date goodsUpdateDate;
    @Column(name = "PRC_UPDATE_DATE")
    private Date prcUpdateDate;
    @Column(name = "RECOMMEND_SCORE1")
    private String recommendScore1;
    @Column(name = "RECOMMEND_SCORE2")
    private String recommendScore2;
    @Column(name = "GOODS_SET_TYPE")
    private String goodsSetType;
    @Column(name = "GOODS_SET_COUNT")
    private String goodsSetCount;
    @Column(name = "GOODS_OPTION")
    private String goodsOption;
    @Column(name = "GOODS_GRP_ID")
    private String goodsGrpId;
    @Column(name = "GOODS_GRP_NO")
    private String goodsGrpNo;
    @Column(name = "HIDDEN_KEYWORD")
    private String hiddenKeyword;
    @Column(name = "HASH_TAGS")
    private String hashTags;
    @Column(name = "SCORE1")
    private String score1;
    @Column(name = "SCORE2")
    private String score2;
    @Column(name = "SCORE3")
    private String score3;
    @Column(name = "SCORE4")
    private String score4;
    @Column(name = "SCORE5")
    private String score5;
    @Column(name = "SUB_CATE_CODE")
    private String subCateCode;
    @Column(name = "SUB_CATE_NAME")
    private String subCateName;
    @Column(name = "ERP_ID")
    private String erpId;

}
