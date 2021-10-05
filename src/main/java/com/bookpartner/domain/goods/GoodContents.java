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
@Table(name = "GOOD_CONTENTS")
public class GoodContents {

    @Id
    @Column(name = "CONT_GOODS_ID")
    private String contGoodsId;
    @Column(name = "CONT_COVER_TEXT")
    private String contCoverText;
    @Column(name = "CONT_INDEX")
    private String contIndex;
    @Column(name = "CONT_KEYWORD")
    private String contKeyword;
    @Column(name = "CONT_INTRO")
    private String contIntro;
    @Column(name = "CONT_DETAIL")
    private String contDetail;
    @Column(name = "CONT_AUTHOR_WORD")
    private String contAuthorWord;
    @Column(name = "CONT_PBL_REVIEW")
    private String contPblReview;
    @Column(name = "CONT_THIS_STORY")
    private String contThisStory;
    @Column(name = "CONT_COVER_IMAGE_YN")
    private String contCoverImageYn;
    @Column(name = "CONT_REVIEW_CNT")
    private String contReviewCnt;
    @Column(name = "CONT_CUST_WRITE_CNT")
    private String contCustWriteCnt;
    @Column(name = "CONT_MAIN_IMAGE_YN")
    private String contMainImageYn;
    @Column(name = "CONT_LIBRO_REVIEW_YN")
    private String contLibroReviewYn;
    @Column(name = "CONT_RECOM_FLAG")
    private String contRecomFlag;
    @Column(name = "CONT_REWARD")
    private String contReward;
    @Column(name = "CONT_UPDATED_DATE")
    private java.sql.Date contUpdatedDate;

}
