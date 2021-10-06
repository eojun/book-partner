package com.bookpartner.web.dto;

import lombok.*;
import oracle.ucp.proxy.annotation.GetCreator;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverBookSellingCountDto {
	
	private String goods_id;			// 책의 고유값(GOODS_ID). CP의 상품코드(PK)를 입력합니다
	private String sale_cnt;			// 네이버를 통해 판매된 량을 입력합니다
	
}
