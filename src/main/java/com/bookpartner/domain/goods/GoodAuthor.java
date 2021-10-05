package com.bookpartner.domain.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "GOOD_AUTHOR")
public class GoodAuthor {

    @EmbeddedId // 복합키
    private GoodAuthorPK goodAuthorPK;
    @Column(name = "GAUT_MALL_ID")
    private String gautMallId;
    @Column(name = "GAUT_AUT_ID")
    private String gautAutId;
    @Column(name = "GAUT_NAME")
    private String gautName;
    @Column(name = "GAUT_ENAME")
    private String gautEname;
    @Column(name = "GAUT_SEQ")
    private String gautSeq;
    @Column(name = "GAUT_INFO")
    private String gautInfo;
    @Column(name = "GAUT_CREATE_DATE")
    private java.sql.Date gautCreateDate;
    @Column(name = "GAUT_STATUS")
    private String gautStatus;

}
