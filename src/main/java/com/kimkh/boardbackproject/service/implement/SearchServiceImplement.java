package com.kimkh.boardbackproject.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.Search.GetPopularListResponseDto;
import com.kimkh.boardbackproject.repository.SearchLogRepository;
import com.kimkh.boardbackproject.repository.resultSet.SearchWordResultSet;
import com.kimkh.boardbackproject.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {
    
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        
        List<SearchWordResultSet> resultSets = new ArrayList<>();

        try {
            
            resultSets = searchLogRepository.getPopularWordList();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }
    
}
