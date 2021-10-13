package com.bookpartner.service;

import com.bookpartner.TestConfig;
import com.bookpartner.domain.payinfo.PayInfo;
import com.bookpartner.domain.payinfo.PayInfoRepository;
import com.bookpartner.web.dto.*;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestConfig.class)
public class PayInfoServiceTest {

    @Autowired
    private PayInfoService payInfoService;

    @Before
    public void setup(){
        String startDate = "2100-01-01";
        String endDate = "2100-01-02";
        String admJoinsId = "NAVERBOOK";

        // payNo, payPrice, payAuthDate, payStatus
        payInfoService.insertPayInfo("TEST1", "10000", startDate, "4003", admJoinsId);
        payInfoService.insertPayInfo("TEST2", "20000", startDate, "4003", admJoinsId);
    }

    @After
    public void teardown(){
        payInfoService.deletePayInfo("TEST1");
        payInfoService.deletePayInfo("TEST2");
    }

    @Test
    void getOrderCount(){
        //Given
        String startDate = "2100-01-01";
        String endDate = "2100-01-02";
        String admJoinsId = "NAVERBOOK";

        // When
        long count = payInfoService.getOrderCount(startDate, endDate, admJoinsId);

        // Then
        System.out.println("count = " + count);
        assertThat(count).isEqualTo(2);

    }

    @Test
    void getSalesSumDto(){
        String startDate = "2021-09-13";
        String endDate = "2021-09-14";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        SalesSumDto dto = payInfoService.getSalesSum(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void getSalesSumDailyDto(){
        String startDate = "2021-09-13";
        String endDate = "2021-09-14";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        List<SalesSumDailyDto> dto = payInfoService.getSalesSumDaily(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void getSalesOrderDailyDto(){
        String startDate = "2021-09-13";
        String endDate = "2021-09-14";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        List<SalesOrderDailyDto> dto = payInfoService.getSalesOrderDaily(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void getSalesCalcOrderSum(){
        String startDate = "2021-09-13";
        String endDate = "2021-09-14";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        SalesCalcOrderSumDto dto = payInfoService.getSalesCalcOrderSum(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void getSalesCalcCancelSum(){
        String startDate = "2021-09-13";
        String endDate = "2021-09-14";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        SalesCalcOrderSumDto dto = payInfoService.getSalesCalcCancelSum(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void getSalesCalcResult(){
        String startDate = "2021-09-02";
        String endDate = "2021-09-03";
        String admJoinsId = "NAVERBOOK";

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        List<SalesCalcResultDto> dto = payInfoService.getSalesCalcResult(startDate, endDate, admJoinsId);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

}
