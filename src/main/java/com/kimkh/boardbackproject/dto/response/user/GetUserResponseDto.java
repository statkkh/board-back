package com.kimkh.boardbackproject.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;
import com.kimkh.boardbackproject.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto  extends ResponseDto{
    
    private String email;
    private String nickname;
    private String profileImage;

    private GetUserResponseDto(String code, String message, UserEntity userEntity) {
        super(code, message);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImageUrl();
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity) {
        GetUserResponseDto result = new GetUserResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }    

}
