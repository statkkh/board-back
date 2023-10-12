package com.kimkh.boardbackproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimkh.boardbackproject.dto.response.search.GetPopularListResponseDto;
import com.kimkh.boardbackproject.dto.response.search.GetRelationWordListResponseDto;
import com.kimkh.boardbackproject.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    
    private final SearchService searchService;

    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList(){
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }

    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity<? super GetRelationWordListResponseDto> getRelationList(
        @PathVariable("searchWord") String searchWord
    ){  
        ResponseEntity<? super GetRelationWordListResponseDto>  response = searchService.getRelationList(searchWord);
        return response;
    }
}
