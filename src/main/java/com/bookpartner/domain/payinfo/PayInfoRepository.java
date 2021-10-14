package com.bookpartner.domain.payinfo;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.bookpartner.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.bookpartner.domain.payinfo.QPayInfo.payInfo;

@RequiredArgsConstructor
@Repository
public class PayInfoRepository {

    private final JPAQueryFactory queryFactory;


    @Transactional
    public PayInfo getPayInfo(String pay_no){
        return queryFactory.selectFrom(payInfo).where(payInfo.payNo.eq(pay_no)).fetchOne();
    }

    @Transactional
    public long getOrderCount(String startDate, String endDate, String admJoinsId){
        long count = queryFactory.selectFrom(payInfo).where(
                payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                , payInfo.payPrimary.eq("1")
                , payInfo.payAuthDate.between(startDate, endDate))
                .fetchCount();
        return count;
    }

    @Transactional
    public long getCancelOrderCount(String startDate, String endDate, String admJoinsId){
        long count = queryFactory.selectFrom(payInfo).where(
                payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                , payInfo.payPrimary.eq("1")
                , payInfo.orders.ordStatus.eq("4043")
                , payInfo.payAuthDate.between(startDate, endDate))
                .fetchCount();
        return count;
    }

    @Transactional
    public SalesSumDto getSalesSum(String startDate, String endDate, String admJoinsId){

        int pay_price_order = queryFactory.select(payInfo.payPrice.sum()).from(payInfo).where(
                payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                , payInfo.orders.ordStatus.notIn("4001", "4002")
                , payInfo.payStatus.eq("2601")
                , payInfo.payAuthDate.between(startDate, endDate)
        ).fetchFirst();

        int pay_price_cancel = queryFactory.select(payInfo.payPrice.sum()).from(payInfo).where(
                payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                , payInfo.orders.ordStatus.notIn("4001", "4002")
                , payInfo.payStatus.eq("2603")
                , payInfo.payAuthDate.between(startDate, endDate)
        ).fetchFirst();

        List<Tuple> pay_price_virtual_tuple = queryFactory.select(payInfo.payPrice.sum().coalesce(0), payInfo.count()).from(payInfo).where(
                payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                , payInfo.orders.ordStatus.notIn("4001", "4002", "4043")
                , payInfo.payStatus.eq("2602")
                , payInfo.payAuthDate.between(startDate, endDate)
        ).fetch();

        Tuple tuple = pay_price_virtual_tuple.get(0);

        int pay_price_virtual = tuple.get(payInfo.payPrice.sum().coalesce(0));
        Long pay_price_virtual_count = tuple.get(payInfo.count());

        int order_order_count = (int) getOrderCount(startDate, endDate, admJoinsId);
        int order_cancel_count = (int) getCancelOrderCount(startDate, endDate, admJoinsId);

        SalesSumDto salesSum = new SalesSumDto(pay_price_order, pay_price_cancel, pay_price_virtual, pay_price_virtual_count.intValue(), order_order_count, order_cancel_count);
        return salesSum;
    }

    @Transactional
    public List<SalesSumDailyDto> getSalesSumDaily(String startDate, String endDate, String admJoinsId){
        QPayInfo payInfoMain = new QPayInfo("payInfoMain");
        QPayInfo payInfoSub = new QPayInfo("payInfoSub");

        return queryFactory
                .select(Projections.constructor(SalesSumDailyDto.class,
                        payInfoMain.payAuthDate.as("pay_auth_date"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum())
                                .from(payInfoSub)
                                .where(payInfoSub.payStatus.eq("2601"),
                                        payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        , payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                        , payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_price_order"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum())
                                .from(payInfoSub)
                                .where(payInfoSub.payStatus.eq("2603"),
                                        payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_price_cancel"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.count())
                                .from(payInfoSub)
                                .where(payInfoSub.payPrimary.eq("1"),
                                        payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_order_count"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.count())
                                .from(payInfoSub)
                                .where(payInfoSub.payPrimary.eq("1")
                                        ,payInfoSub.orders.ordStatus.eq("4043")
                                        ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_cancel_count"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum())
                                .from(payInfoSub)
                                .where(payInfoSub.orders.ordStatus.notIn("4043","4002","4001"),
                                        payInfoSub.payStatus.eq("2602")
                                        ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_price_virtual"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.count())
                                .from(payInfoSub)
                                .where(payInfoSub.orders.ordStatus.notIn("4043","4002","4001"),
                                        payInfoSub.payStatus.eq("2602")
                                        ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                ),"pay_price_virtual_count")
                        )
                )
                .from(payInfoMain)
                .where(
                        payInfoMain.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                        , payInfoMain.orders.ordStatus.notIn("4001", "4002")
                        , payInfoMain.payAuthDate.between(startDate, endDate)
                )
                .groupBy(payInfoMain.payAuthDate)
                .orderBy(payInfoMain.payAuthDate.asc())
                .fetch();
    }

    @Transactional
    public List<SalesOrderDailyDto> getSalesOrderDaily(String startDate, String endDate, String admJoinsId){
        QPayInfo payInfoMain = new QPayInfo("payInfoMain");
        QPayInfo payInfoSub = new QPayInfo("payInfoSub");

        return queryFactory
                .select(Projections.constructor(SalesOrderDailyDto.class,
                        payInfoMain.payAuthDate.as("pay_auth_date"),
                        payInfoMain.orders.ordOrderId.as("ord_order_id"),
                        payInfoMain.orders.ordCustName.max().as("ord_cust_name"),
                        payInfoMain.orders.ordGoodName.max().as("ord_good_name"),
                        payInfoMain.orders.ordStatus.max()
                                .when("4008").then("입금대기")
                                .when("4008").then("입금대기")
                                .when("4009").then("주문완료")
                                .when("4015").then("재고확인중")
                                .when("4017").then("전송")
                                .when("4031").then("출고중")
                                .when("4041").then("출고")
                                .when("4042").then("주문일시보류")
                                .when("4043").then("전체취소")
                                .otherwise("")
                                .as("ord_status_txt"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                .from(payInfoSub)
                                .where(payInfoSub.payStatus.eq("2601"),payInfoSub.orders.ordOrderId.eq(payInfoMain.orders.ordOrderId)
                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_price"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                .from(payInfoSub)
                                .where(payInfoSub.payStatus.eq("2603"),payInfoSub.orders.ordOrderId.eq(payInfoMain.orders.ordOrderId)
                                      ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_can_price"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                .from(payInfoSub)
                                .where(payInfoSub.payStatus.eq("2602")
                                        ,payInfoSub.orders.ordStatus.notIn("4043")
                                        ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                        ,payInfoSub.orders.ordOrderId.eq(payInfoMain.orders.ordOrderId))
                                ,"pay_price_virtual")
                        )
                )
                .from(payInfoMain)
                .where(
                        payInfoMain.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                        , payInfoMain.orders.ordStatus.notIn("4001", "4002")
                        , payInfoMain.payAuthDate.between(startDate, endDate)
                )
                .groupBy(payInfoMain.orders.ordOrderId, payInfoMain.payAuthDate)
                .orderBy(payInfoMain.payAuthDate.asc(), payInfoMain.orders.ordOrderId.asc())
                .fetch();
    }

    @Transactional
    public SalesCalcOrderSumDto getSalesCalcOrderSum(String startDate, String endDate, String admJoinsId){

        List<Tuple> payInfos = queryFactory
                .select(
                        payInfo.payCode,
                        payInfo.payPrice.sum().coalesce(0).as("pay_price"),
                        payInfo.count().as("order_count")
                )
                .from(payInfo)
                .where(
                        payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                        , payInfo.orders.ordStatus.notIn("4001", "4002")
                        , payInfo.payAuthDate.between(startDate, endDate)
                        , payInfo.payStatus.eq("2601")
                )
                .groupBy(payInfo.payCode)
                .fetch();

        int pay_price = 0;
        int order_count = 0;
        SalesCalcOrderSumDto dto = new SalesCalcOrderSumDto();

        for(Tuple t : payInfos){
            String payCode = t.get(payInfo.payCode);
            int price = t.get(payInfo.payPrice.sum().coalesce(0).as("pay_price"));
            int count = ((Long)t.get(payInfo.count().as("order_count"))).intValue();

            pay_price += price;
            order_count += count;

            if(payCode.equals("4459")){
                dto.setSt_billing(price);
                dto.setSt_billing_count(count);
            }else if(payCode.equals("4418")){
                dto.setMileage(price);
                dto.setMileage_count(count);
            }else if(payCode.equals("4429")){
                dto.setPoint(price);
                dto.setPoint_count(count);
            }else if(payCode.equals("4421")){
                dto.setDis_coupon(price);
                dto.setDis_coupon_count(count);
            }else if(payCode.equals("4402") || payCode.equals("4435") || payCode.equals("4484") || payCode.equals("4468")){
                dto.setCell_phone(dto.getCell_phone() + price);
                dto.setCell_phone_count(dto.getCell_phone_count() + count);
            }else if(payCode.equals("4440")){
                dto.setOk_cashback(price);
                dto.setOk_cashback_count(count);
            }else if(payCode.equals("4442")){
                dto.setGoods_dis_coupon(price);
                dto.setGoods_dis_coupon_count(count);
            }else if(payCode.equals("4403") || payCode.equals("4446") || payCode.equals("4485") || payCode.equals("4469")){
                dto.setCredit_card(dto.getCredit_card() + price);
                dto.setCredit_card_count(dto.getCredit_card_count() + count);
            }else if(payCode.equals("4444")){
                dto.setHy_credit_card(price);
                dto.setHy_credit_card_count(count);
            }else if(payCode.equals("4400") || payCode.equals("4448") || payCode.equals("4415") || payCode.equals("4482") || payCode.equals("4466")){
                dto.setVirtual(dto.getVirtual() + price);
                dto.setVirtual_count(dto.getVirtual_count() + count);
            }else if(payCode.equals("4438")){
                dto.setDeposit(price);
                dto.setDeposit_count(count);
            }else if(payCode.equals("4439")){
                dto.setLibro_cash(price);
                dto.setLibro_cash_count(count);
            }else if(payCode.equals("4451")){
                dto.setExchange_amount(price);
                dto.setExchange_amount_count(count);
            }else if(payCode.equals("4404") || payCode.equals("4447") || payCode.equals("4483") || payCode.equals("4467")){
                dto.setTransfer_account(dto.getTransfer_account() + price);
                dto.setTransfer_account_count(dto.getTransfer_account_count() + count);
            }else if(payCode.equals("4481")){
                dto.setCyber_money(price);
                dto.setCyber_money_count(count);
            }

        }

        dto.setPay_price(pay_price);
        dto.setOrder_count(order_count);

        return dto;
    }

    @Transactional
    public SalesCalcOrderSumDto getSalesCalcCancelSum(String startDate, String endDate, String admJoinsId){

        List<Tuple> payInfos = queryFactory
                .select(
                        payInfo.payCode,
                        payInfo.payPrice.sum().coalesce(0).as("pay_price"),
                        payInfo.count().as("order_count")
                )
                .from(payInfo)
                .where(
                        payInfo.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                        , payInfo.orders.ordStatus.notIn("4001", "4002")
                        , payInfo.payAuthDate.between(startDate, endDate)
                        , payInfo.payStatus.eq("2603")
                )
                .groupBy(payInfo.payCode)
                .fetch();

        int pay_price = 0;
        int order_count = 0;
        SalesCalcOrderSumDto dto = new SalesCalcOrderSumDto();

        for(Tuple t : payInfos){
            String payCode = t.get(payInfo.payCode);
            int price = t.get(payInfo.payPrice.sum().coalesce(0).as("pay_price"));
            int count = ((Long)t.get(payInfo.count().as("order_count"))).intValue();

            pay_price += price;
            order_count += count;

            if(payCode.equals("4459")){
                dto.setSt_billing(price);
                dto.setSt_billing_count(count);
            }else if(payCode.equals("4418")){
                dto.setMileage(price);
                dto.setMileage_count(count);
            }else if(payCode.equals("4429")){
                dto.setPoint(price);
                dto.setPoint_count(count);
            }else if(payCode.equals("4421")){
                dto.setDis_coupon(price);
                dto.setDis_coupon_count(count);
            }else if(payCode.equals("4402") || payCode.equals("4435") || payCode.equals("4484") || payCode.equals("4468")){
                dto.setCell_phone(dto.getCell_phone() + price);
                dto.setCell_phone_count(dto.getCell_phone_count() + count);
            }else if(payCode.equals("4440")){
                dto.setOk_cashback(price);
                dto.setOk_cashback_count(count);
            }else if(payCode.equals("4442")){
                dto.setGoods_dis_coupon(price);
                dto.setGoods_dis_coupon_count(count);
            }else if(payCode.equals("4403") || payCode.equals("4446") || payCode.equals("4485") || payCode.equals("4469")){
                dto.setCredit_card(dto.getCredit_card() + price);
                dto.setCredit_card_count(dto.getCredit_card_count() + count);
            }else if(payCode.equals("4444")){
                dto.setHy_credit_card(price);
                dto.setHy_credit_card_count(count);
            }else if(payCode.equals("4400") || payCode.equals("4448") || payCode.equals("4415") || payCode.equals("4482") || payCode.equals("4466")){
                dto.setVirtual(dto.getVirtual() + price);
                dto.setVirtual_count(dto.getVirtual_count() + count);
            }else if(payCode.equals("4438")){
                dto.setDeposit(price);
                dto.setDeposit_count(count);
            }else if(payCode.equals("4439")){
                dto.setLibro_cash(price);
                dto.setLibro_cash_count(count);
            }else if(payCode.equals("4451")){
                dto.setExchange_amount(price);
                dto.setExchange_amount_count(count);
            }else if(payCode.equals("4404") || payCode.equals("4447") || payCode.equals("4483") || payCode.equals("4467")){
                dto.setTransfer_account(dto.getTransfer_account() + price);
                dto.setTransfer_account_count(dto.getTransfer_account_count() + count);
            }else if(payCode.equals("4481")){
                dto.setCyber_money(price);
                dto.setCyber_money_count(count);
            }
        }

        dto.setPay_price(pay_price);
        dto.setOrder_count(order_count);

        return dto;
    }

    @Transactional
    public List<SalesCalcResultDto> getSalesCalcResult(String startDate, String endDate, String admJoinsId) {
        QPayInfo payInfoMain = new QPayInfo("payInfoMain");
        QPayInfo payInfoSub = new QPayInfo("payInfoSub");

        List<SalesCalcResultDto> dto = queryFactory.select(
                Projections.constructor(SalesCalcResultDto.class,
                        payInfoMain.payAuthDate.as("pay_auth_date"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                        .from(payInfoSub)
                                        .where(payInfoSub.payStatus.eq("2601")
                                                ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                                ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_price"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.count())
                                        .from(payInfoSub)
                                        .where(payInfoSub.payStatus.eq("2601")
                                                ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                                ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_price_count"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                        .from(payInfoSub)
                                        .where(payInfoSub.payStatus.eq("2603")
                                                ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                                , payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_can_price"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.count())
                                        .from(payInfoSub)
                                        .where(payInfoSub.payStatus.eq("2603")
                                                ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                                ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate))
                                ,"pay_can_price_count"),
                        ExpressionUtils.as(JPAExpressions.select(payInfoSub.payPrice.sum().coalesce(0))
                                        .from(payInfoSub)
                                        .where(payInfoSub.payStatus.eq("2603")
                                                ,payInfoSub.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                                                ,payInfoSub.orders.ordStatus.notIn("4001", "4002")
                                                ,payInfoSub.payAuthDate.eq(payInfoMain.payAuthDate)
                                                ,payInfoSub.payCode.eq("4438"))
                                ,"deposit_can")
                )
                )
                .from(payInfoMain)
                .where(payInfoMain.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                        , payInfoMain.orders.ordStatus.notIn("4001", "4002")
                        , payInfoMain.payAuthDate.between(startDate, endDate))
                .groupBy(payInfoMain.payAuthDate)
                .orderBy(payInfoMain.payAuthDate.asc())
                .fetch();

        return dto;
    }

}
