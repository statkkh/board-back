package com.kimkh.boardbackproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimkh.boardbackproject.entity.BoardEntity;
// import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

    boolean existsByBoardNumber(Integer boardNumber);
    
    BoardEntity findByBoardNumber(Integer boardNumber);

}
