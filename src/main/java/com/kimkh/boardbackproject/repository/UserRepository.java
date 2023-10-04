package com.kimkh.boardbackproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kimkh.boardbackproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);

    UserEntity findByEmail(String email);    
}
