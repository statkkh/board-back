package com.kimkh.boardbackproject.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.user.GetSignInUserResponseDto;
import com.kimkh.boardbackproject.dto.response.user.GetUserResponseDto;
import com.kimkh.boardbackproject.entity.UserEntity;
import com.kimkh.boardbackproject.repository.UserRepository;
import com.kimkh.boardbackproject.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserResponseDto.success(userEntity);
       
    }
    
}
