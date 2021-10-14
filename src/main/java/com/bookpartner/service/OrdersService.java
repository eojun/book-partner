package com.bookpartner.service;

import com.bookpartner.domain.orders.Orders;
import com.bookpartner.domain.orders.OrdersRepository;
import com.bookpartner.domain.payinfo.PayInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Transactional
    public Orders getOrders(String ordId){
        return ordersRepository.getOrders(ordId);
    }

}
