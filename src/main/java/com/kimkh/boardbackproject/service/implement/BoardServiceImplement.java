package com.kimkh.boardbackproject.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;
import com.kimkh.boardbackproject.entity.BoardEntity;
import com.kimkh.boardbackproject.entity.BoardImageEntity;
import com.kimkh.boardbackproject.entity.BoardViewEntity;
import com.kimkh.boardbackproject.repository.BoardImageRepository;
import com.kimkh.boardbackproject.repository.BoardRepository;
import com.kimkh.boardbackproject.repository.BoardViewRepository;
import com.kimkh.boardbackproject.repository.UserRepository;
import com.kimkh.boardbackproject.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardImageRepository boardImageRepository;    

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();
            Integer boardNumber = boardEntity.getBoardNumber();

            List<BoardImageEntity> boardImageEntities = new ArrayList<>();
            for (String boardImage: boardImageList) {
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber, boardImage);
                boardImageEntities.add(boardImageEntity);
            }

            boardImageRepository.saveAll(boardImageEntities);            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();            
        }
        return PostBoardResponseDto.success();        
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        BoardViewEntity boardViewEntity = null;
        List<BoardImageEntity> boardImageEntities = new ArrayList<>();

        try {
            boardViewEntity = boardViewRepository.findByBoardNumber(boardNumber);
            if (boardViewEntity == null) return GetBoardResponseDto.notExistBoard();

            boardImageEntities = boardImageRepository.findByBoardNumber(boardNumber);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();     
        }

        return GetBoardResponseDto.success(boardViewEntity, boardImageEntities);
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        List<BoardViewEntity> boardViewEntities = new ArrayList<>();
        try {

            boardViewEntities = boardViewRepository.findByOrderByWriteDatetimeDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();             
        }

        return GetLatestBoardListResponseDto.success(boardViewEntities);
    }
    
}
