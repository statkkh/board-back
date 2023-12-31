package com.kimkh.boardbackproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimkh.boardbackproject.entity.BoardViewEntity;

@Repository
public interface BoardViewRepository extends JpaRepository<BoardViewEntity, Integer>{
    BoardViewEntity findByBoardNumber(Integer boardNumber);

    List<BoardViewEntity> findByOrderByWriteDatetimeDesc();
    List<BoardViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String email);
     // 정렬 기준 복수개 이상 하기 //
    List<BoardViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDesc(String writeDatetime);
    List<BoardViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content); 
}
