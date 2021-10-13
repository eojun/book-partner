package com.bookpartner.service;

import com.bookpartner.domain.payinfo.PayInfoRepository;
import com.bookpartner.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PayInfoService {
    private final PayInfoRepository payInfoRepository;

    @Transactional
    public long getOrderCount(String startDate, String endDate, String admJoinsId){
        return payInfoRepository.getOrderCount(startDate, endDate, admJoinsId);
    }

    @Transactional
    public long getCancelOrderCount(String startDate, String endDate, String admJoinsId){
        return payInfoRepository.getCancelOrderCount(startDate, endDate, admJoinsId);
    }

    @Transactional
    public SalesSumDto getSalesSum(String startDate, String endDate, String admJoinsId){
        return payInfoRepository.getSalesSum(startDate, endDate, admJoinsId);
    }

    @Transactional
    public List<SalesSumDailyDto> getSalesSumDaily(String startDate, String endDate, String admJoinsId){
        return payInfoRepository.getSalesSumDaily(startDate, endDate, admJoinsId);
    }


    public List<SalesOrderDailyDto> getSalesOrderDaily(String startDate, String endDate, String admJoinsId) {
        return payInfoRepository.getSalesOrderDaily(startDate, endDate, admJoinsId);
    }

    public SalesCalcOrderSumDto getSalesCalcOrderSum(String startDate, String endDate, String admJoinsId) {
        return payInfoRepository.getSalesCalcOrderSum(startDate, endDate, admJoinsId);
    }

    public SalesCalcOrderSumDto getSalesCalcCancelSum(String startDate, String endDate, String admJoinsId) {
        return payInfoRepository.getSalesCalcCancelSum(startDate, endDate, admJoinsId);
    }

    public List<SalesCalcResultDto> getSalesCalcResult(String startDate, String endDate, String admJoinsId) {
        return payInfoRepository.getSalesCalcResult(startDate, endDate, admJoinsId);
    }

    @Transactional
    public void insertPayInfo(String payNo, String payPrice, String payAuthDate, String payStatus, String ordJoinsId){
        payInfoRepository.insertPayInfo(payNo, payPrice, payAuthDate, payStatus, ordJoinsId);
    }

    @Transactional
    public void deletePayInfo(String payNo){
        payInfoRepository.deletePayInfo(payNo);
    }
}
