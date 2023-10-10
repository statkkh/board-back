package com.kimkh.boardbackproject.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;

public class PatchNicknameResponseDto extends ResponseDto{
    
    private PatchNicknameResponseDto(String code, String message){
        super(code, message);
    }

    public static ResponseEntity<PatchNicknameResponseDto> success(){
        PatchNicknameResponseDto result = new PatchNicknameResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.OK).body(result);   
    }
    public static ResponseEntity<ResponseDto> duplicatedNickname(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_NICKNAME, ResponseMessage.DUPLICATED_NICKNAME);
        return ResponseEntity.status(HttpStatus.OK).body(result);   
    }
}
