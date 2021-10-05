package com.bookpartner.domain.ordergoods;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.bookpartner.web.dto.SalesRankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bookpartner.domain.ordergoods.QOrderGoods.orderGoods;

@RequiredArgsConstructor
@Repository
public class OrderGoodsRepository {
    private final JPAQueryFactory queryFactory;

    @Transactional
    public List<SalesRankDto> getSalesRank(String startDate, String endDate, String admJoinsId){

        List<SalesRankDto> list  = queryFactory.select(Projections.constructor(SalesRankDto.class,
                orderGoods.orgGoodsName.as("org_goods_name"),
                orderGoods.orgBusiName.as("org_buis_name"),
                orderGoods.orderGoodsPK.orgGoodsId.as("org_goods_id"),
                orderGoods.orgOrdNums.sum().coalesce(0).as("org_ord_nums"),
                /*orderGoods.orgSalePrice.coalesce(0).as("org_sale_price")*/
                orderGoods.orgOrdNums.coalesce(0).asNumber().multiply(orderGoods.orgSalePrice.coalesce(0)).sum().as("totalprice")
                ))
                .from(orderGoods)
                .where(
                        orderGoods.orders.ordStatus.gt("4001"),
                        orderGoods.orgState.notIn("4130", "4131", "4160"),
                        orderGoods.orders.ordDate.between(startDate, endDate),
                        orderGoods.orders.ordJoinsId.eq(admJoinsId.toUpperCase().trim())
                )
                .groupBy(orderGoods.orgGoodsName, orderGoods.orgBusiName, orderGoods.orderGoodsPK.orgGoodsId)
                .orderBy(orderGoods.orgOrdNums.sum().coalesce(0).desc(), orderGoods.orgOrdNums.coalesce(0).asNumber().multiply(orderGoods.orgSalePrice.coalesce(0)).sum().desc())
                .offset(0) // 0부터 시작(Zero index)
                .limit(50) // 최대 50건 조회
                .fetch();


        return list;
    }


}
