package com.kimkh.boardbackproject.service;

import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.dto.request.user.PatchNicknameRequestDto;
import com.kimkh.boardbackproject.dto.request.user.PatchProfileImageRequestDto;
import com.kimkh.boardbackproject.dto.response.user.GetSignInUserResponseDto;
import com.kimkh.boardbackproject.dto.response.user.GetUserResponseDto;
import com.kimkh.boardbackproject.dto.response.user.PatchNicknameResponseDto;
import com.kimkh.boardbackproject.dto.response.user.PatchProfileImageResponseDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);

    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto , String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto , String email);
}
