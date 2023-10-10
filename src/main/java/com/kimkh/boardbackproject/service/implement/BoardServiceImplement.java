package com.kimkh.boardbackproject.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kimkh.boardbackproject.dto.request.board.PatchBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostBoardRequestDto;
import com.kimkh.boardbackproject.dto.request.board.PostCommentRequestDto;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.board.DeleteBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetBoardResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetCommentListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetFavoriteListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetLatestBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.GetUserBoardListResponseDto;
import com.kimkh.boardbackproject.dto.response.board.IncreaseViewCountResponseDto;
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
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PutFavoriteResponseDto.notExistBoard();

            boolean exitsByEmail = userRepository.existsByEmail(email);
            if(!exitsByEmail)  return PutFavoriteResponseDto.notExistUser();  

            boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(email, boardNumber);

            FavoriteEntity favoriteEntity = new FavoriteEntity(email, boardNumber);
            
            // description : favorite exist or notexist //
            if(isFavorite) {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }
            else{
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseCommentCount();
            }

            boardRepository.save(boardEntity);

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
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {

        List<BoardViewEntity> boardViewEntities = new ArrayList<>();

       try {
        
        boolean existsUser = userRepository.existsByEmail(email);
        if(!existsUser) return GetUserBoardListResponseDto.notExistUser();

        boardViewEntities = boardViewRepository.findByWriterEmailOrderByWriteDatetime();

       } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
       }

       return GetUserBoardListResponseDto.success(boardViewEntities);
    }
    
    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber,String email) {

        try {
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if( boardEntity == null) return PostCommentResponseDto.notExistBoard();

            boolean existsUser = userRepository.existsByEmail(email);
            if(!existsUser) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber,  String email) {
                
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PatchBoardResponseDto.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PatchBoardResponseDto.notExistBoard();

            boolean equalWriter = boardEntity.getWriterEmail().equals(email);
            if (!equalWriter) return PatchBoardResponseDto.noPermission();

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();

            boardImageRepository.deleteByBoardNumber(boardNumber);

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

        return  PatchBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {

        try {
            
            boolean existsUser = userRepository.existsByEmail(email);
            if(!existsUser) return DeleteBoardResponseDto.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return DeleteBoardResponseDto.notExistBoard();
            // 게시물 작성한 이메일과 삭제하려는 주체가 동일여부 
            boolean isWriter = boardEntity.getWriterEmail().equals(email);
            if(!isWriter) return DeleteBoardResponseDto.noPermission();

            commentRepository.deleteByBoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber(boardNumber);
            boardImageRepository.deleteByBoardNumber(boardNumber);
            boardRepository.delete(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return IncreaseViewCountResponseDto.notExistBoard();    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.success();
        
    }


    
}
