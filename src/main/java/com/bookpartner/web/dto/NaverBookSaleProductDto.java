package com.bookpartner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NaverBookSaleProductDto {
	
	private static final long serialVersionUID = 1L;
	
	private String isbn; //ISBN
	private String goods_id; //상품ID
	private String count; //판매지수
	private String price; //판매가격
	private String price_disrate; //할인율
	private String point; //적립금
	private String point_rate; //적립율
	private String page_url; // CP페이지URL
	private String page_url_mobile; // CP페이지URL, 모바일용 페이지
	private String delivery_cost; //배송료. 배송료(관련 정보가 없으면 비워둡니다.)
	private String delivery_schedule; //배송료예정일. 배송일정(관련 정보가 없으면 비워둡니다.)
	private String event;		// 해당 상품을 구입할 때 받을 수 있는 이벤트 관련 내용 표기(15자 내외 텍스트)

	
}
