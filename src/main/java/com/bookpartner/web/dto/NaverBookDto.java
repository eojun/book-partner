package com.bookpartner.web.dto;

import com.bookpartner.domain.searchindexgoods.SearchIndexGoods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
public class NaverBookDto {

	
	private String isbn; //ISBN (기준13자리, 불가피한경우 - 10자리, 잡지는 ISSN 8자리)
	private String ebook_isbn;		//전자책 매핑을 위한 전자책 isbn을 입력합니다. ------- 필드명과 위치 확인 부탁드려요!
	private String isbn_additional_sign;	// 도서정가제 예외 처리를 위해 ISBN 부가기호를 입력합니다. conects book은 없음?
	private String section; //상품구분(도서만 제공하는 CP인경우 공백, 도서 이외 제공시 코드별 상품구분 자료 별도 제공 요망). NaverBook은 사용하지 않음. Null 값으로
	private String category_no; // 대표분류 (각CP별 상황에 따름)
	private String category_no2; //중복분류 (각CP별 상황에 따름)
	private String goods_id; // 상품ID
	private String title; // (관칭관제 +) 본서명
	private String subtitle; // 부제 (+ 총서명 또는 시리즈명 + 총서권호 또는 시리즈권호)
	private String volume; // 볼륨(1,2,상,하) ------- 필드 없는 것 같아서 추가해 봤어요!
	private String book_price; // 정가
	private String author_name; // 저자명 ------- 저자명이 n명인 경우 어떻게 구분하나요?
	private String author_code; // 저자코드
	private String translator; // 역자명
	private String publisher; // 출판사명
	private String publish_day; // 출판일
	private String pages; // 페이지수
	private String image_url; // 책표지이미지 중 URL (W/H = 70/100 이상, 140/200 이하인 이미지). 작은 커버이미지 경로
	private String image_url_2; // 책표지 이미지 대 URL (W/H = 140/200 초과 이미지 또는 'image_url'보다 큰 이미지). 큰 커버 이미지 경로
	private String description; // 책 소개말. 태그포함 , html 태그는 <p>, <br>, <b>태그만 허용.
	private String author_intro; // 작가 소개말. 태그포함, html 태그는 <p>, <br>, <b>태그만 허용
	private String contents; // 목차. 태그포함, html 태그는 <p>, <br>, <b>태그만 허용
	private String feature; // 부록정보, 없는 경우 공백. NaverBook 사용하지 않음.
	private String isadult; // 19금일 경우 숫자.  청유물 여부로 청유물인 경우 Y, 아닌 경우 N이나 null로 둡니다.

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String toString() {
		// Daum..?
		return "DaumEntity [isbn=" + isbn 
				+ ", ebook_isbn=" + ebook_isbn
				+ ", isbn_additional_sign=" + isbn_additional_sign
				+ ", section=" + section
				+ ", category_no=" + category_no 
				+ ", category_no2="	+ category_no2 
				+ ", goods_id=" + goods_id
				+ ", title=" + title
				+ ", subtitle=" + subtitle
				+ ", volume=" + volume
				+ ", book_price=" + book_price + ", author_name=" + author_name
				+ ", author_code=" + author_code + ", translator=" + translator
				+ ", publisher=" + publisher 
//				+ ", publisher_code=" + publisher_code 
				+ ", publish_day=" + publish_day 
				+ ", pages=" + pages 
				+ ", image_url=" + image_url + ", image_url_2="	+ image_url_2 
//				+ ", image_date=" + image_date 
				+ ", description="	+ description + ", author_intro=" + author_intro
				+ ", contents=" + contents + ", feature=" + feature
//				+ ", review_cnt=" + review_cnt 
//				+ ", evaluate_cnt="	+ evaluate_cnt 
//				+ ", evaluate_point=" + evaluate_point
				+ ", isadult=" + isadult + "]";
	}

	public NaverBookDto(
			String isbn,
			String category_no,
			String category_no2,
			String goods_id,
			String title,
			String subtitle,
			String book_price,
			String author_name,
			String translator,
			String publisher,
			String publish_day,
			String pages,
			String image_url,
			String image_url_2,
			String description,
			String author_intro,
			String contents,
			String isadult
	){
		this.isbn = isbn;
		this.category_no = category_no;
		this.category_no2 = category_no2;
		this.goods_id = goods_id;
		this.title = title;
		this.subtitle = subtitle;
		this.book_price = book_price;
		this.author_name = author_name;
		this.translator = translator;
		this.publisher = publisher;
		this.publish_day = publish_day;
		this.pages = pages;
		this.image_url = image_url;
		this.image_url_2 = image_url_2;
		this.description = description;
		this.author_intro = author_intro;
		this.contents = contents;
		this.isadult = isadult;

		this.ebook_isbn = "<<<ebook_isbn>>>";
		this.isbn_additional_sign = "<<<isbn_additional_sign>>>";
		this.section = "<<<section>>>";
		this.volume = "<<<volume>>>";
		this.author_code = "<<<author_code>>>";
		this.feature = "<<<feature>>>";

	}

	public NaverBookDto(SearchIndexGoods entity){

	}

	public NaverBookDto(SearchIndexGoods entity, String category_no, String category_no2, String translator, String pages, String image_url, String image_url2, String description, String author_intro, String contents) {
		this.isbn = entity.getBarcode() != null ? "<<<isbn>>>" + entity.getBarcode() : "<<<isbn>>>" + entity.getIsbn();
		this.ebook_isbn = "<<<ebook_isbn>>>";
		this.isbn_additional_sign = "<<<isbn_additional_sign>>>";
		this.section = "<<<section>>>";
		this.category_no = "<<<category_no>>>" + category_no;
		this.category_no2 = "<<<category_no2>>>" + category_no2;
		this.goods_id = "<<<goods_id>>>" + entity.getGoodsId();
		this.title = "<<<title>>>" + entity.getGoodsName();
		this.subtitle = "<<<subtitle>>>" + entity.getGoodsSubName();
		this.volume = "<<<volume>>>";
		this.book_price = "<<<book_price>>>" + entity.getPrcPrice();
		this.author_name = "<<<author_name>>>" + entity.getAuthorName();
		this.author_code = "<<<author_code>>>";
		this.translator = "<<<translator>>>" + translator;
		this.publisher = "<<<publisher>>>" + entity.getPubName().replaceAll("펴냄","").replaceAll("출간","").replaceAll("도서출판","").replaceAll("(주)","");
		this.publish_day = "<<<publish_day>>>" + sdf.format(entity.getPubDate());
		this.pages = "<<<pages>>>" + pages;
		this.image_url = "<<<image_url>>>" + image_url;
		this.image_url_2 = "<<<image_url_2>>>" + image_url2;
		this.description = "<<<description>>>" + description;
		this.author_intro = "<<<author_intro>>>" + author_intro;
		this.contents = "<<<contents>>>" + contents;
		this.feature = "<<<feature>>>";

		if(entity.getGoodsDegree() != null && entity.getGoodsDegree().equals("19")){
			this.isadult = "Y";
		}else{
			this.isadult = "";
		}
	}


	public NaverBookDto(SearchIndexGoods entity, String translator, String pages, String image_url, String image_url2, String description, String author_intro, String contents) {
		this.isbn = entity.getBarcode() != null ? "<<<isbn>>>" + entity.getBarcode() : "<<<isbn>>>" + entity.getIsbn();
		this.ebook_isbn = "<<<ebook_isbn>>>";
		this.isbn_additional_sign = "<<<isbn_additional_sign>>>";
		this.section = "<<<section>>>";
		this.category_no = "<<<category_no>>>" + entity.getCatCode();
		this.category_no2 = "<<<category_no2>>>" + entity.getSubCateCode();
		this.goods_id = "<<<goods_id>>>" + entity.getGoodsId();
		this.title = "<<<title>>>" + entity.getGoodsName();
		this.subtitle = "<<<subtitle>>>" + entity.getGoodsSubName();
		this.volume = "<<<volume>>>";
		this.book_price = "<<<book_price>>>" + entity.getPrcPrice();
		this.author_name = "<<<author_name>>>" + entity.getAuthorName();
		this.author_code = "<<<author_code>>>";
		this.translator = "<<<translator>>>" + translator;
		this.publisher = "<<<publisher>>>" + entity.getPubName().replaceAll("펴냄","").replaceAll("출간","").replaceAll("도서출판","").replaceAll("(주)","");
		this.publish_day = "<<<publish_day>>>" + sdf.format(entity.getPubDate());
		this.pages = "<<<pages>>>" + pages;
		this.image_url = "<<<image_url>>>" + image_url;
		this.image_url_2 = "<<<image_url_2>>>" + image_url2;
		this.description = "<<<description>>>" + description;
		this.author_intro = "<<<author_intro>>>" + author_intro;
		this.contents = "<<<contents>>>" + contents;
		this.feature = "<<<feature>>>";

		if(entity.getGoodsDegree() != null && entity.getGoodsDegree().equals("19")){
			this.isadult = "Y";
		}else{
			this.isadult = "";
		}
	}

	public NaverBookDto(SearchIndexGoods entity, String translator){
		this.goods_id = entity.getGoodsId();
		this.translator = translator;
	}

	public NaverBookDto(
			String isbn,
			String ebook_isbn,
			String isbn_additional_sign,
			String section,
			String category_no,
			String category_no2,
			String goods_id,
			String title,
			String subtitle,
			String volume,
			String book_price,
			String author_name,
			String author_code,
			String translator,
			String publisher,
			String publish_day,
			String pages,
			String image_url,
			String image_url_2,
			String description,
			String author_intro,
			String contents,
			String feature,
			String isadult
	){
		this.isbn = isbn;
		this.ebook_isbn = ebook_isbn;
		this.isbn_additional_sign = isbn_additional_sign;
		this.section = section;
		this.category_no = category_no;
		this.category_no2 = category_no2;
		this.goods_id = goods_id;
		this.title = title;
		this.subtitle = subtitle;
		this.volume = volume;
		this.book_price = book_price;
		this.author_name = author_name;
		this.author_code = author_code;
		this.translator = translator;
		this.publisher = publisher;
		this.publish_day = publish_day;
		this.pages = pages;
		this.image_url = image_url;
		this.image_url_2 = image_url_2;
		this.description = description;
		this.author_intro = author_intro;
		this.contents = contents;
		this.feature = feature;
		this.isadult  = isadult;
	}

}
