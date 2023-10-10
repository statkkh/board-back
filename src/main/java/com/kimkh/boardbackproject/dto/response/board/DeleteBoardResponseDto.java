package com.kimkh.boardbackproject.dto.response.board;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;


import lombok.Getter;

@Getter
public class DeleteBoardResponseDto extends ResponseDto{
    
    private DeleteBoardResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<DeleteBoardResponseDto> success() {
        DeleteBoardResponseDto result = new DeleteBoardResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    } 

    public static ResponseEntity<DeleteBoardResponseDto> notExistUser(){
        DeleteBoardResponseDto result = new DeleteBoardResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);              
    }    

    public static ResponseEntity<DeleteBoardResponseDto> notExistBoard(){
        DeleteBoardResponseDto result = new DeleteBoardResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);              
    }

    public static ResponseEntity<DeleteBoardResponseDto> noPermission(){
        DeleteBoardResponseDto result = new DeleteBoardResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);              
    }    
    
}
