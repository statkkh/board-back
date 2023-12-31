package com.kimkh.boardbackproject.service;


import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.request.board.PatchBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostCommentRequestDto;
import com.kimkh.boardbackproject.dto.response.board.DeleteBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetCommentListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetFavoriteListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetSearchBoardListReponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetTop3BoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetUserBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.IncreaseViewCountResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PatchBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostCommentResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto > postComment(PostCommentRequestDto dto, Integer boardNumber, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto > getCommentList(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();    
    ResponseEntity <? super GetUserBoardListResponseDto> getUserBoardList( String email);
    ResponseEntity <? super  GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity <?super GetSearchBoardListReponseDto> getSearchBoardList(String searchWord, String preSearchWord);
    
    ResponseEntity<? super PutFavoriteResponseDto > putFavorite(Integer boardNumber, String email);

    ResponseEntity<? super PatchBoardResponseDto > patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);

    ResponseEntity<? super  IncreaseViewCountResponseDto > increaseViewCount(Integer boardNumber);
}
