package com.bookpartner.domain.orders;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrdersStatusAttributeConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        switch(attribute){
            case "입금대기": return "4008";
            case "주문완료": return "4009";
            case "재고확인중": return "4015";
            case "전송": return "4017";
            case "출고중": return "4031";
            case "출고": return "4041";
            case "주문일시보류": return "4042";
            case "전체취소": return "4043";
            default: return "";
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        switch(dbData){
            case "4008": return "입금대기";
            case "4009": return "주문완료";
            case "4015": return "재고확인중";
            case "4017": return "전송";
            case "4031": return "출고중";
            case "4041": return "출고";
            case "4042": return "주문일시보류";
            case "4043": return "전체취소";
            default: return "";
        }
    }
}
