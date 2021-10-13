package com.bookpartner.service;

import com.bookpartner.TestConfig;
import com.bookpartner.web.dto.NaverBookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest // 통합테스트. 모든 Bean을 등록하게
@Import(TestConfig.class)
//@ExtendWith(SpringExtension.class)
public class NaverBookServiceTest {

    @Autowired
    private NaverBookService naverBookService;

    @Test
    void getNaverBookDtoList(){

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기

        List<NaverBookDto> list = naverBookService.getNaverBookDto();

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

    @Test
    void makeNaverBookBestSeller() throws Exception {

        naverBookService.doNaverBookBestsellerProductDto("/Users/st/Desktop/test/");
    }

}
