package com.bookpartner.service;

import com.bookpartner.web.dto.NaverBookBestsellerProductDto;
import com.bookpartner.web.dto.NaverBookDto;
import com.bookpartner.web.dto.NaverBookSaleProductDto;
import com.bookpartner.web.dto.NaverBookSellingCountDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NaverBookService {

    private static final Logger logger = LoggerFactory.getLogger(NaverBookService.class);

    @Autowired
    @Qualifier("oracleSqlSessionTemplate")
    protected SqlSession sqlSession;

    public List<NaverBookDto> getNaverBookDto(NaverBookDto naverBookDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookDto", naverBookDto);
    }

    public List<NaverBookSaleProductDto> getNaverBookSaleProductDto(NaverBookSaleProductDto naverBookSaleProductDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookSaleProductDto", naverBookSaleProductDto);
    }

    public List<NaverBookSellingCountDto> getNaverBookSellingCount(NaverBookSellingCountDto naverBookSellingCountDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookSellingCountDto", naverBookSellingCountDto);
    }

    public List<NaverBookBestsellerProductDto> getNaverBookBestsellerProductDto(NaverBookBestsellerProductDto naverBookBestsellerProductDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookBestsellerProductDto", naverBookBestsellerProductDto);
    }

    public List<NaverBookBestsellerProductDto> getNaverBookBestsellerCategory(NaverBookBestsellerProductDto naverBookBestsellerProductDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookBestsellerCategory", naverBookBestsellerProductDto);
    }

    public List<NaverBookBestsellerProductDto> getNaverBookBestsellerProductDto1(NaverBookBestsellerProductDto naverBookBestsellerProductDto) {
        return sqlSession.selectList("NaverBookMapper.getNaverBookBestsellerProductDto1", naverBookBestsellerProductDto);
    }


    /*
     * 연동명	1. 서지 - 파일 생성용
     * 업데이트 주기	일별
     * 설명	파일명은 biblio_업체이름_yyyymmdd.txt로 최근 5일 간의 신규/수정 데이터를 매일 만듭니다. 굵게 표시된 항목은 기존 CP 입점 시 받던 정보보다 추가된 정보입니다.
     * 		최초 입점시에는 전체 데이터를 생성합니다.
     * 일별 신규/수정 데이터를 매일 만듭니다"
     */
    public void doNaverBookDto(String fileDir) throws Exception{

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String naverBookFileName = "biblio_conectsbook_" + sdf.format(calendar.getTime()) + ".txt";
        String naverBookFilePath = fileDir + naverBookFileName;

        NaverBookDto naverBookDto = new NaverBookDto();

        List<NaverBookDto> naverBookDtoList = this.getNaverBookDto(naverBookDto);

        if(null != naverBookDtoList && naverBookDtoList.size() > 0 ){
            PrintWriter pw = new PrintWriter(naverBookFilePath);
            for(NaverBookDto Dto : naverBookDtoList){
                pw.println(Dto.getIsbn());
                pw.println(Dto.getEbook_isbn());
                pw.println(Dto.getIsbn_additional_sign());
                pw.println(Dto.getSection());
                pw.println(Dto.getCategory_no());
                pw.println(Dto.getCategory_no2());
                pw.println(Dto.getGoods_id());
                pw.println(Dto.getTitle());
                pw.println(Dto.getSubtitle());
                pw.println(Dto.getVolume());
                pw.println(Dto.getBook_price());
                pw.println(Dto.getAuthor_name());
                pw.println(Dto.getAuthor_code());
                pw.println(Dto.getTranslator());
                pw.println(Dto.getPublisher());
                pw.println(Dto.getPublish_day());
                pw.println(Dto.getPages());
                pw.println(Dto.getImage_url());
                pw.println(Dto.getImage_url_2());
                pw.println(Dto.getDescription());
                pw.println(Dto.getAuthor_intro());
                pw.println(Dto.getContents());
                pw.println(Dto.getFeature());
                pw.println(Dto.getIsadult());
            }
            pw.close();
        }

        logger.debug("End doGetNaverBookDto");
    }

    public void doNaverBookSaleProductDto(String fileDir) throws Exception{

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String naverBookFileName = "product_conectsbook_" + sdf.format(calendar.getTime()) + ".txt";
        String naverBookFilePath = fileDir + naverBookFileName;

        NaverBookSaleProductDto naverBookDto = new NaverBookSaleProductDto();

        List<NaverBookSaleProductDto> naverBookDtoList = this.getNaverBookSaleProductDto(naverBookDto);

        if(null != naverBookDtoList && naverBookDtoList.size() > 0 ){
            PrintWriter pw = new PrintWriter(naverBookFilePath);
            for(NaverBookSaleProductDto Dto : naverBookDtoList){
                pw.println(Dto.getIsbn());
                pw.println(Dto.getGoods_id());
                pw.println(Dto.getCount());
                pw.println(Dto.getPrice());
                pw.println(Dto.getPrice_disrate());
                pw.println(Dto.getPoint());
                pw.println(Dto.getPoint_rate());
                pw.println(Dto.getPage_url());
                pw.println(Dto.getPage_url_mobile());
                pw.println(Dto.getDelivery_cost());
                pw.println(Dto.getDelivery_schedule());
                pw.println(Dto.getEvent());			//  맵퍼에서 직접 뽑지 않고, 나중에서 서비스에서 따로 뽑아오기 지금 진행중인 이벤트 내용. 15자로 예)구매자 전원 손난로 증정
            }
            pw.close();
        }
    }

    /*
     * 연동명	3. 판매량 정보 - 파일 생성용.
     * 업데이트 주기	일별
     * 설명	파일명은 sale_업체이름_yyyymmdd.txt로 전날 판매된 모든 판매량 정보를 만듭니다
     */
    public void doNaverBookSellingCount(String fileDir) throws Exception{

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String naverBookFileName = "sale_conectsbook_" + sdf.format(calendar.getTime()) + ".txt";
        String naverBookFilePath = fileDir + naverBookFileName;

        NaverBookSellingCountDto naverBookDto = new NaverBookSellingCountDto();

        List<NaverBookSellingCountDto> naverBookDtoList = this.getNaverBookSellingCount(naverBookDto);

        if(null != naverBookDtoList && naverBookDtoList.size() > 0 ){
            PrintWriter pw = new PrintWriter(naverBookFilePath);
            for(NaverBookSellingCountDto Dto : naverBookDtoList){
                pw.println(Dto.getGoods_id());
                pw.println(Dto.getSale_cnt());
            }
            pw.close();
        }
    }

    /*
     * 연동명	4. 베스트셀러 품정보 - 파일 생성용.
     * 업데이트 주기	일별
     * 설명	파일명은 bestseller_업체이름_yyyymmdd.txt로 그날 판매하는 모든 상품 정보를 만듭니다.
     * 		신규/수정/판매중지 반영을 위해 전일 데이터를 모두 삭제하고 신규 데이터로 덮어씌워 반영합니다
     */
    public void doNaverBookBestsellerProductDto(String fileDir) throws Exception{

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String naverBookFileName = "bestseller_conectsbook_" + sdf.format(calendar.getTime()) + ".txt";
        String naverBookFilePath = fileDir + naverBookFileName;

        NaverBookBestsellerProductDto naverBookDto = new NaverBookBestsellerProductDto();

        List<NaverBookBestsellerProductDto> naverBookCategoryList = this.getNaverBookBestsellerCategory(naverBookDto);

        if(null != naverBookCategoryList && naverBookCategoryList.size() > 0 ){
            PrintWriter pw = new PrintWriter(naverBookFilePath);

            for(NaverBookBestsellerProductDto categoryDto : naverBookCategoryList){
                naverBookDto.setBest_category(categoryDto.getBest_category());

                List<NaverBookBestsellerProductDto> naverBookDtoList = this.getNaverBookBestsellerProductDto1(naverBookDto);

                if(null != naverBookDtoList && naverBookDtoList.size() > 0 ){
                    for(NaverBookBestsellerProductDto Dto : naverBookDtoList){
                        pw.println(Dto.getBest_category());
                        pw.println(Dto.getRank());
                        pw.println(Dto.getRank_updown());
                        pw.println(Dto.getIsbn());
                        pw.println(Dto.getGoods_id());
                    }
                }
            }
            pw.close();
        }
    }

}
