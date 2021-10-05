package com.bookpartner.service;

import com.bookpartner.TestConfig;
import com.bookpartner.web.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(TestConfig.class)
public class OrderGoodsServiceTest {

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Test
    void getSalesRank(){
        String startDate = "2021-09-02";
        String endDate = "2021-09-03";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        List<SalesRankDto> dto = orderGoodsService.getSalesRank(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

}
