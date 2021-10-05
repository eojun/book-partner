package com.bookpartner.service;

import com.bookpartner.domain.ordergoods.OrderGoodsRepository;
import com.bookpartner.web.dto.SalesRankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderGoodsService {
    private final OrderGoodsRepository orderGoodsRepository;

    @Transactional
    public List<SalesRankDto> getSalesRank(String startDate, String endDate, String admJoinsId){
        return orderGoodsRepository.getSalesRank(startDate, endDate, admJoinsId);
    }

}
