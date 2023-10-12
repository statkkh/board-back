package com.kimkh.boardbackproject.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;
import com.kimkh.boardbackproject.repository.resultSet.SearchWordResultSet;

import lombok.Getter;

@Getter
public class GetRelationWordListResponseDto extends ResponseDto {
    
    private List<String> relativeWordList;

    private GetRelationWordListResponseDto(String code,String message, List<SearchWordResultSet> resultSets){
        super(code, message);
        List<String> relativeWordList = new ArrayList<>();
        for(SearchWordResultSet resultSet : resultSets){
            String word = resultSet.getSearchWord();
            relativeWordList.add(word);
        }
        this.relativeWordList = relativeWordList;
    }

    public static ResponseEntity< GetRelationWordListResponseDto> success(List<SearchWordResultSet> resultSets){
        GetRelationWordListResponseDto result =new GetRelationWordListResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
