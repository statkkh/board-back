package com.kimkh.boardbackproject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimkh.boardbackproject.entity.FavoriteEntity;
import com.kimkh.boardbackproject.entity.primaryKey.FavoritePk;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity , FavoritePk>{

    boolean existsByUserEmailAndBoardNumber(String userEmail, Integer boardNumber);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
    
}
