package com.bookpartner.domain.searchindexgoods;

import com.bookpartner.web.dto.NaverBookDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.bookpartner.domain.goods.QGoodAuthor;
import com.bookpartner.web.dto.NaverBookDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.Expressions.asString;
import static com.bookpartner.domain.goods.QGoodAuthor.goodAuthor;
import static com.bookpartner.domain.goods.QGoodCode.goodCode;
import static com.bookpartner.domain.goods.QGoodContents.goodContents;
import static com.bookpartner.domain.goods.QGoodImage.goodImage;
import static com.bookpartner.domain.goods.QGoodsAdd.goodsAdd;
import static com.bookpartner.domain.searchindexgoods.QSearchIndexGoods.searchIndexGoods;

@RequiredArgsConstructor
@Repository
public class SearchIndexGoodsRepository {

    private final JPAQueryFactory queryFactory;

    private final EntityManager em;

    @Autowired
    @Qualifier("oracleSqlSessionTemplate")
    protected SqlSession sqlSession;

    public List<NaverBookDto> getNaverBookDtoList(){
        QGoodAuthor translator = new QGoodAuthor("translator");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date today_obj = new java.util.Date();
        java.util.Date beforeYesterday_obj = new java.util.Date(today_obj.getTime() + (1000*60*60*24*-2));

        String today = sdf.format(today_obj);
        String beforeYesterday = sdf.format(beforeYesterday_obj);


        List<NaverBookDto> dtos = queryFactory.select(Projections.constructor(NaverBookDto.class,
                ExpressionUtils.as(searchIndexGoods, "entity"),
                ExpressionUtils.as(Expressions.stringTemplate("function('WM_CONCAT',{0})",translator.gautName), "translator")
                /*ExpressionUtils.as(JPAExpressions.select(goodAuthor.gautName)
                        .from(goodAuthor)
                        .where(goodAuthor.goodAuthorPK.gautType.eq("02"),
                                goodAuthor.goodAuthorPK.gautGoodsId.eq(searchIndexGoods.goodsId))
                        ,"translator")*/
                /*ExpressionUtils.as(JPAExpressions.select(goodsAdd.goodsPages)
                        .from(goodsAdd)
                        .where(goodsAdd.goodsId.eq(searchIndexGoods.goodsId))
                        ,"pages"),
                ExpressionUtils.as(JPAExpressions.select(
                        asString("//s3.ap-northeast-2.amazonaws.com/libro.image")
                                .append(asString(goodImage.imgImagePath))
                                .append(asString(goodImage.imgFileName))
                )
                        .from(goodImage)
                        .where(goodImage.goodImagePK.imgGoodsId.eq(searchIndexGoods.goodsId)
                                , goodImage.goodImagePK.imgKind.eq("0")
                                , goodImage.goodImagePK.imgSize.eq("1")
                        )
                        ,"image_url"),
                ExpressionUtils.as(JPAExpressions.select(
                        asString("//s3.ap-northeast-2.amazonaws.com/libro.image")
                                .append(asString(goodImage.imgImagePath))
                                .append(asString(goodImage.imgFileName))
                )
                        .from(goodImage)
                        .where(goodImage.goodImagePK.imgGoodsId.eq(searchIndexGoods.goodsId)
                                , goodImage.goodImagePK.imgKind.eq("0")
                                , goodImage.goodImagePK.imgSize.eq("3")
                        )
                        ,"image_url2"),
                ExpressionUtils.as(JPAExpressions.select(
                        asString(
                                goodContents.contIntro.substring(0, 1000).coalesce("").asString().append("<br><br>").append(goodContents.contDetail.substring(0, 1000).coalesce("")).toString()
                                        .replaceAll(System.getProperty("line.separator"), "<br>")
                                        .replaceAll("\\t", "  ")
                                        .replaceAll("A HREF=\"/books/book_detail.asp?", "A HREF=\"http://www.libro.co.kr/product/bookDetail?partner=daumshop&")
                                        .replaceAll("a href=\"/books/book_detail.asp?", "A HREF=\"http://www.libro.co.kr/product/bookDetail?partner=daumshop&")
                        )
                        )
                                .from(goodContents)
                                .where(goodContents.contGoodsId.eq(searchIndexGoods.goodsId))
                ,"description"),
                ExpressionUtils.as(asString(JPAExpressions.select(goodAuthor.gautInfo)
                        .from(goodAuthor)
                        .where(goodAuthor.goodAuthorPK.gautGoodsId.eq(searchIndexGoods.goodsId),
                                goodAuthor.goodAuthorPK.gautType.ne("02"))
                        .toString()
                        .replaceAll(System.getProperty("line.separator"), "<br>")
                        .replaceAll("\\t", "  "))
                ,"author_intro"),
                ExpressionUtils.as(JPAExpressions.select(goodContents.contIndex)
                        .from(goodContents)
                        .where(goodContents.contGoodsId.eq(searchIndexGoods.goodsId))
                ,"contents")*/
        ))
                .from(searchIndexGoods)
                .leftJoin(translator).on(searchIndexGoods.goodsId.eq(translator.goodAuthorPK.gautGoodsId))
                .where(
                        searchIndexGoods.mallId.in("1", "2", "4", "5", "6"),
                        searchIndexGoods.goodsStatus.ne("판매대기"),
                        searchIndexGoods.goodsId.notIn("0060000657635", "0060000693466", "0060000764302", "0060000006861", "0060000007141",
                                "0060000008704", "0060000006586", "0060000007809"),
                        searchIndexGoods.goodsUpdateDate.between(Date.valueOf(beforeYesterday), Date.valueOf(today))
                                .or(searchIndexGoods.prcUpdateDate.between(Date.valueOf(beforeYesterday), Date.valueOf(today))),
                        translator.goodAuthorPK.gautType.eq("02")
                )
                .fetch();

        return dtos;
    }

    public String getCategoryNo(String goodsId, String flag){
        return queryFactory.select(goodCode.codLibroCode)
                .from(goodCode)
                .where(goodCode.codGoodsId.eq(goodsId), goodCode.codPrimeFlag.eq(flag))
                .fetchFirst();
    }

    public String getGoodsPage(String goodsId){
        return queryFactory.select(goodsAdd.goodsPages)
                .from(goodsAdd)
                .where(goodsAdd.goodsId.eq(goodsId))
                .fetchFirst();
    }

    public String getGoodImage(String goodsId, String pSize){
        String image_url = queryFactory
                .select(
                    asString("//s3.ap-northeast-2.amazonaws.com/libro.image")
                    .append(asString(goodImage.imgImagePath))
                    .append(asString(goodImage.imgFileName))
                    .as("image_url")
                )
                .from(goodImage)
                .where(goodImage.goodImagePK.imgGoodsId.eq(goodsId)
                        , goodImage.goodImagePK.imgKind.eq("0")
                        , goodImage.goodImagePK.imgSize.eq(pSize)
                )
                .fetchFirst();
        return image_url;
    }

    public String getContents(String goodsId){
        String contents = queryFactory.select(goodContents.contIndex)
                .from(goodContents)
                .where(goodContents.contGoodsId.eq(goodsId))
                .fetchFirst();

        if(contents != null && contents.length()>1000)
            contents = contents.substring(0, 1000);
        else if(contents == null)
            contents = "";

        return contents;
    }

    public String getDescription(String goodsId){
        String desc = "";

        String cont_intro = queryFactory.select(goodContents.contIntro)
                .from(goodContents)
                .where(goodContents.contGoodsId.eq(goodsId))
                .fetchFirst();

        String cont_detail = queryFactory.select(goodContents.contDetail)
                .from(goodContents)
                .where(goodContents.contGoodsId.eq(goodsId))
                .fetchFirst();

        if(cont_intro!=null && cont_intro.length()>1000){
            cont_intro = cont_intro.substring(0,1000);
        }else if(cont_intro == null){
            cont_intro = "";
        }

        if(cont_detail!=null && cont_detail.length()>1000){
            cont_detail = cont_detail.substring(0,1000);
        }else if(cont_detail == null){
            cont_detail = "";
        }

        desc = cont_intro + "<br><br>" + cont_detail;
        desc
            .replaceAll(System.getProperty("line.separator"),"<br>")
            .replaceAll("\\t", "  ")
            .replaceAll("A HREF=\"/books/book_detail.asp?", "A HREF=\"http://www.libro.co.kr/product/bookDetail?partner=daumshop&")
            .replaceAll("a href=\"/books/book_detail.asp?","A HREF=\"http://www.libro.co.kr/product/bookDetail?partner=daumshop&");

        return desc;
    }

    public String getTranslator(String goodsId){
        List<String> authorNames = queryFactory.select(goodAuthor.gautName)
                .from(goodAuthor)
                .where(goodAuthor.goodAuthorPK.gautType.eq("02"),
                        goodAuthor.goodAuthorPK.gautGoodsId.eq(goodsId))
                .fetch();

        String translator = "";
        if(authorNames != null){
            translator = authorNames.stream().map(v -> v.toString()).collect(Collectors.joining(","));
        }
        return translator;
    }

    public String getAuthorIntro(String goodsId){
        String gaut_info = queryFactory.select(goodAuthor.gautInfo)
                .from(goodAuthor)
                .where(goodAuthor.goodAuthorPK.gautGoodsId.eq(goodsId),
                        goodAuthor.goodAuthorPK.gautType.ne("02"))
                .fetchFirst();

        if(gaut_info != null){
            if(gaut_info.length()>1000)
                gaut_info = gaut_info.substring(0, 1000);

            gaut_info.replaceAll(System.getProperty("line.separator"),"");
        }


        return gaut_info;
    }


    public List<NaverBookDto> getNaverBookDtoListByMybatis(){

        NaverBookDto naverBookDto = new NaverBookDto();

        List<NaverBookDto> list = sqlSession.selectList("NaverBookMapper.getNaverBookEntity", naverBookDto);

        return list;
    }


}
