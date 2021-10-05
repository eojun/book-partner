package com.bookpartner.web.controller;

import com.bookpartner.common.StringUtil;
import com.bookpartner.common.UserDetailsImpl;
import com.bookpartner.service.OrderGoodsService;
import com.bookpartner.service.PayInfoService;
import com.bookpartner.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sales")
public class SalesController extends BaseController{

    private final PayInfoService payInfoService;
    private final OrderGoodsService orderGoodsService;

    @GetMapping("/salesListDay")
    public String salesListDay(){
        return "sales/salesListDay";
    }

    @GetMapping("/salesListDayOrderList")
    public String salesListDayOrderList(){
        return "sales/salesListDayOrderList";
    }

    @GetMapping("/salesListCalc")
    public String salesListCalc(){
        return "sales/salesListCalc";
    }

    @GetMapping("/salesRankList")
    public String salesRankList(){
        return "sales/salesRankList";
    }

    // 일일 주문내역(일자별),일일 주문내역(주문목록) 합계 Ajax
    @PostMapping(path="/ajaxSalesListDay")
    public String ajaxSalesListDay(Model model, HttpServletResponse response, HttpServletRequest request){

        UserDetailsImpl user =  getSecurityContextUser();

        Map  requestParam = getParameterMap();
        requestParam.put("searchStartDate", StringUtil.nvl(requestParam, "searchStartDate"));
        requestParam.put("searchEndDate", StringUtil.nvl(requestParam, "searchEndDate"));
        requestParam.put("admJoinsId", user.getJoinsId());

        SalesSumDto salesSum = payInfoService.getSalesSum(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());
        List<SalesSumDailyDto> salesSumDaily = payInfoService.getSalesSumDaily(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());

        model.addAttribute("result", salesSum);
        model.addAttribute("salesSumDaily", salesSumDaily);
        model.addAttribute("requestParam", requestParam);

        return "sales/ajaxFragments/ajaxSalesListDay";
    }

    @PostMapping(path="/ajaxSalesListDayOrderList" )
    public String ajaxSalesListDayOrderList(Model model, HttpServletResponse response,HttpServletRequest request){

        UserDetailsImpl user =  getSecurityContextUser();

        Map  requestParam = getParameterMap();
        requestParam.put("searchStartDate", StringUtil.nvl(requestParam, "searchStartDate"));
        requestParam.put("searchEndDate", StringUtil.nvl(requestParam, "searchEndDate"));
        requestParam.put("admJoinsId", user.getJoinsId());

        SalesSumDto salesSum = payInfoService.getSalesSum(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());

        List<SalesOrderDailyDto> salesOrderDailyList = payInfoService.getSalesOrderDaily(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());

        model.addAttribute("result", salesSum);
        model.addAttribute("salesOrderDailyList",salesOrderDailyList);
        model.addAttribute("requestParam", requestParam);

        return "sales/ajaxFragments/ajaxSalesListDayOrderList";
    }

    @PostMapping(path="/ajaxSalesListCalc")
    public String ajaxSalesListCalc(Model model, HttpServletResponse response,HttpServletRequest request){

        UserDetailsImpl user =  getSecurityContextUser();

        Map  requestParam = getParameterMap();
        requestParam.put("searchStartDate", StringUtil.nvl(requestParam, "searchStartDate"));
        requestParam.put("searchEndDate", StringUtil.nvl(requestParam, "searchEndDate"));
        requestParam.put("admJoinsId", user.getJoinsId());

        SalesCalcOrderSumDto salesCalcOrderSumDto = payInfoService.getSalesCalcOrderSum(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());
        SalesCalcOrderSumDto salesCalcCancelSumDto = payInfoService.getSalesCalcCancelSum(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());
        List<SalesCalcResultDto> salesCalcResultDtoList = payInfoService.getSalesCalcResult(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());

        model.addAttribute("calc_order_sum",salesCalcOrderSumDto);		// 합계 결제액
        model.addAttribute("calc_cancel_sum",salesCalcCancelSumDto);	// 합계 취소액
        model.addAttribute("calc_detail_order_list",salesCalcResultDtoList);	// 상세 > 주문,취소

        model.addAttribute("requestParam", requestParam);

        return "sales/ajaxFragments/ajaxSalesListCalc";
    }

    // 상품주문 순위 Ajax
    @RequestMapping(path="/ajaxSalesRankList" , method = RequestMethod.POST)
    public String ajaxSalesRankList(Model model, HttpServletResponse response,HttpServletRequest request){

        UserDetailsImpl user =  getSecurityContextUser();

        Map  requestParam = getParameterMap();
        requestParam.put("searchStartDate", StringUtil.nvl(requestParam, "searchStartDate"));
        requestParam.put("searchEndDate", StringUtil.nvl(requestParam, "searchEndDate"));
        requestParam.put("admJoinsId", user.getJoinsId());

        List<SalesRankDto> salesRankDtoList = orderGoodsService.getSalesRank(requestParam.get("searchStartDate").toString(), requestParam.get("searchEndDate").toString(), requestParam.get("admJoinsId").toString());

        model.addAttribute("rank_list",salesRankDtoList);
        model.addAttribute("requestParam", requestParam);


        return "sales/ajaxFragments/ajaxSalesRankList";
    }

}
