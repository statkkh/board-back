package com.kimkh.boardbackproject.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetFavoriteListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PutFavoriteResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetCommentListResponseDto;
import com.kimkh.boardbackproject.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    

    private final BoardService boardService;
    
    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity <? super GetCommentListResponseDto> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity <? super GetCommentListResponseDto> response = boardService.getCommentList(boardNumber);
        return response;
    }

    @GetMapping("/latest-list")
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
        @RequestBody @Valid PostBoardRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }    

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String email
    ){
         ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, email);
         return response;
    }  

}
