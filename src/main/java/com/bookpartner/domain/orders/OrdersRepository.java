package com.bookpartner.domain.orders;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrdersRepository {

    private final JPAQueryFactory queryFactory;

}
