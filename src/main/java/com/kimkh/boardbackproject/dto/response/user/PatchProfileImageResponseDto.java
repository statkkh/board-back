package com.kimkh.boardbackproject.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto{
    
    private PatchProfileImageResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<PatchProfileImageResponseDto> success(){
        PatchProfileImageResponseDto result = new PatchProfileImageResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
