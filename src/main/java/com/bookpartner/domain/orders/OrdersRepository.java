package com.bookpartner.domain.orders;

import com.bookpartner.domain.payinfo.PayInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.bookpartner.domain.orders.QOrders.orders;
import static com.bookpartner.domain.payinfo.QPayInfo.payInfo;

@RequiredArgsConstructor
@Repository
public class OrdersRepository {

    private final JPAQueryFactory queryFactory;

    @Transactional
    public Orders getOrders(String ordId){
        return queryFactory.selectFrom(orders).where(orders.ordOrderId.eq(ordId)).fetchOne();
    }

}
