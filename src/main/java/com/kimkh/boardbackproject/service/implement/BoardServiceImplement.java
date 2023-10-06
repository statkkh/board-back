package com.kimkh.boardbackproject.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimkh.boardbackproject.dto.request.board.PatchBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostCommentRequestDto;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetCommentListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetFavoriteListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PatchBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PostCommentResponseDto;
import com.kimkh.boardbackproject.dto.response.board.PutFavoriteResponseDto;
import com.kimkh.boardbackproject.entity.BoardEntity;
import com.kimkh.boardbackproject.entity.BoardImageEntity;
import com.kimkh.boardbackproject.entity.BoardViewEntity;
import com.kimkh.boardbackproject.entity.CommentEntity;
import com.kimkh.boardbackproject.entity.FavoriteEntity;
import com.kimkh.boardbackproject.entity.UserEntity;
import com.kimkh.boardbackproject.repository.BoardImageRepository;
import com.kimkh.boardbackproject.repository.BoardRepository;
import com.kimkh.boardbackproject.repository.BoardViewRepository;
import com.kimkh.boardbackproject.repository.CommentRepository;
import com.kimkh.boardbackproject.repository.FavoriteRepository;
import com.kimkh.boardbackproject.repository.UserRepository;
import com.kimkh.boardbackproject.repository.resultSet.CommentListResultSet;
import com.kimkh.boardbackproject.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
   
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardImageRepository boardImageRepository;

    private final FavoriteRepository favoriteRepository;

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

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {
       
        List<UserEntity> userEntities = new ArrayList<>();
        
        try {
            
            boolean existBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existBoard) return GetFavoriteListResponseDto.notExistBoard();
            
            userEntities = userRepository.findByBoardFavorite(boardNumber);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();  
        }

        return GetFavoriteListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
        
        try {
            
            boolean existBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existBoard) return PutFavoriteResponseDto.notExistBoard();

            boolean exitsByEmail = userRepository.existsByEmail(email);
            if(!exitsByEmail)  return PutFavoriteResponseDto.notExistUser();  

            boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(email, boardNumber);

            FavoriteEntity favoriteEntity = new FavoriteEntity(email, boardNumber);
            
            // description : favorite exist or notexist //
            if(isFavorite) favoriteRepository.delete(favoriteEntity);
            if(!isFavorite) favoriteRepository.save(favoriteEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        
        List<CommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetCommentListResponseDto.notExistBoard(resultSets);

            resultSets = commentRepository.findByCommentList(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber,String email) {

        try {
            
            boolean existsBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existsBoard) return PostCommentResponseDto.notExistBoard();

            boolean existsUser = userRepository.existsByEmail(email);
            if(!existsUser) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
            commentRepository.save(commentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email) {

        try {

            boolean existsUser = userRepository.existsByEmail(email);
            if(!existsUser) return PatchBoardResponseDto.notExistUser();
            // description : Patch 시 boardEntity 필요 //
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PatchBoardResponseDto.notExistBoard();
            
            boolean equalWriter = boardEntity.getWriterEmail().equals(email);
            if(!equalWriter) return PatchBoardResponseDto.noPermission();

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);
            //description : 기존의 게시물 번호 삭제
            boardImageRepository.deleteByBoardNumber(boardNumber);

            List<String > boardImageList = dto.getBoardImageList();
            List<BoardImageEntity> boardImageEntities = new ArrayList<>();

            for(String boardImage : boardImageList){
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber, boardImage);
                boardImageEntities.add(boardImageEntity);
            }
            
            boardImageRepository.saveAll(boardImageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchBoardResponseDto.success();

    }
    
}
