package com.kimkh.boardbackproject.service;

import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.search.GetPopularListResponseDto;
import com.kimkh.boardbackproject.dto.response.search.GetRelationWordListResponseDto;

public interface SearchService {
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationWordListResponseDto> getRelationList(String searchWord);
}
