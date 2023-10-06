package com.kimkh.boardbackproject.service;


import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.request.board.PatchBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostCommentRequestDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetCommentListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetFavoriteListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PatchBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostCommentResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    
    ResponseEntity<? super PostCommentResponseDto > postComment(PostCommentRequestDto requestBody, Integer boardNumber, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto > getCommentList(Integer boardNumber);
   
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();    
    ResponseEntity<? super PutFavoriteResponseDto > putFavorite(Integer boardNumber, String email);
    
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto ,Integer boardNumber,String email);
    
}
