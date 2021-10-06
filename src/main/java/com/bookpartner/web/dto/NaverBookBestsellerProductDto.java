package com.bookpartner.web.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverBookBestsellerProductDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String isbn; //ISBN
	private String goods_id; //상품ID
	private String best_category; //베스트 카테고리
	private String rank; //순위
	private String rank_updown; //순위변동

	public String getBest_category() {
		return best_category;
	}

	public void setBest_category(String best_category) {
		this.best_category = best_category;
	}
	
}
