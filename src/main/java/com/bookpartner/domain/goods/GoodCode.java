package com.bookpartner.domain.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "GOOD_CODE")
public class GoodCode {

    @Id
    @Column(name = "COD_NO")
    private String codNo;
    @Column(name = "COD_MALL_ID")
    private String codMallId;
    @Column(name = "COD_GOODS_ID")
    private String codGoodsId;
    @Column(name = "COD_LIBRO_CODE")
    private String codLibroCode;
    @Column(name = "COD_DISPLAY_SEQ")
    private String codDisplaySeq;
    @Column(name = "COD_PRIME_FLAG")
    private String codPrimeFlag;
    @Column(name = "COD_DATE")
    private Date codDate;
    @Column(name = "COD_PUBLISH_DATE")
    private Date codPublishDate;
    @Column(name = "COD_TITLE")
    private String codTitle;
    @Column(name = "COD_RECOM_CODE")
    private String codRecomCode;
    @Column(name = "COD_CATE_CODE")
    private String codCateCode;

}
