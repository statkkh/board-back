package com.kimkh.boardbackproject.service;

import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.request.auth.SignInRequestDto;
import com.kimkh.boardbackproject.dto.request.auth.SignUpRequestDto;
import com.kimkh.boardbackproject.dto.response.auth.SignInResponseDto;
import com.kimkh.boardbackproject.dto.response.auth.SignUpResponseDto;
public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);    
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
