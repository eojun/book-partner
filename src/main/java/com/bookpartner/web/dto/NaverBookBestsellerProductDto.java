package com.bookpartner.web.dto;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class NaverBookBestsellerProductDto{
	
	private String isbn; //ISBN
	private String goods_id; //상품ID
	private String best_category; //베스트 카테고리
	private String rank; //순위
	private String rank_updown; //순위변동
	
}
