package com.bookpartner.service;

import com.bookpartner.domain.searchindexgoods.SearchIndexGoodsRepository;
import com.bookpartner.web.dto.NaverBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchIndexGoodsService {
    private final SearchIndexGoodsRepository searchIndexGoodsRepository;

    @Transactional
    public List<NaverBookDto> getNaverBookDtoList(){
        return searchIndexGoodsRepository.getNaverBookDtoList();
    }

    /*public List<NaverBookEntity> getNaverBookDtoListByMybatis() {
        return searchIndexGoodsRepository.getNaverBookDtoListByMybatis();
    }*/
}
