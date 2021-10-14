package com.bookpartner.service;

import com.bookpartner.TestConfig;
import com.bookpartner.domain.orders.Orders;
import com.bookpartner.domain.payinfo.PayInfo;
import com.bookpartner.domain.payinfo.PayInfoRepository;
import com.bookpartner.web.dto.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Import(TestConfig.class)
public class PayInfoServiceTest {

    @Autowired
    private PayInfoService payInfoService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    EntityManager em;



    @BeforeEach
    public void setup(){

        Orders orders1 = Orders.builder().ordOrderId("0000").ordRcvName("수령인").ordRcvZip("12345").ordRcvAddr("주소").ordCustName("고객명").ordRcvTel1("01011112222").ordJoinsId("NAVERBOOK").build();

        PayInfo payInfo1 = PayInfo.builder().payPrice(10000).payNo("0001").payStatus("2601").payCode("4418").payAuthDate("2100-01-02").payOrderId("0000").payPrimary("1").build();
        PayInfo payInfo2 = PayInfo.builder().payPrice(10000).payNo("0002").payStatus("2601").payCode("4418").payAuthDate("2100-01-02").payOrderId("0000").payPrimary("1").build();

        em.persist(payInfo1);
        em.persist(payInfo2);
        em.persist(orders1);
        em.flush();
    }

    @AfterEach
    public void teardown(){
        PayInfo payinfo1 = payInfoService.getPayInfo("0001");
        PayInfo payinfo2 = payInfoService.getPayInfo("0002");
        Orders orders1 = ordersService.getOrders("0000");

        em.remove(payinfo1);
        em.remove(payinfo2);
        em.remove(orders1);

        em.flush();
    }

    @Test
    void getOrderCount(){
        // Given
        String startDate = "2100-01-01";
        String endDate = "2100-01-03";
        String admJoinsId = "NAVERBOOK";

        // When
        int count = (int) payInfoService.getOrderCount(startDate, endDate, admJoinsId);

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
