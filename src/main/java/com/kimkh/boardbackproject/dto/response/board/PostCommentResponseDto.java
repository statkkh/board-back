package com.kimkh.boardbackproject.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostCommentResponseDto extends ResponseDto{
    
    private PostCommentResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<PostCommentResponseDto> success(){
        PostCommentResponseDto result = new PostCommentResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<PostCommentResponseDto> notExistBoard(){
        PostCommentResponseDto result = new PostCommentResponseDto(ResponseCode.NOT_EXIST_BOARD,  ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);  
    }

    public static ResponseEntity<PostCommentResponseDto> notExistUser(){
        PostCommentResponseDto result = new PostCommentResponseDto(ResponseCode.NOT_EXIST_USER,  ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);              
    }    

}
