package com.kimkh.boardbackproject.service;

import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.response.user.GetSignInUserResponseDto;
import com.kimkh.boardbackproject.dto.response.user.GetUserResponseDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
}
