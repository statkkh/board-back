package com.kimkh.boardbackproject.service;

import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();    

}
