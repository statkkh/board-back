package com.kimkh.boardbackproject.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kimkh.boardbackproject.common.object.BoardListItem;
import com.kimkh.boardbackproject.dto.response.ResponseCode;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.dto.response.ResponseMessage;
import com.kimkh.boardbackproject.entity.BoardViewEntity;

import lombok.Getter;

@Getter
public class GetSearchBoardListReponseDto extends ResponseDto{
    
    private List<BoardListItem> searchList;
    
    private GetSearchBoardListReponseDto(String code ,String message, List<BoardViewEntity> boardViewEntities){
        super(code, message);
        this.searchList = BoardListItem.getList(boardViewEntities);
    }

    public static ResponseEntity<GetSearchBoardListReponseDto> success(List<BoardViewEntity> boardViewEntities ){
        GetSearchBoardListReponseDto result = new GetSearchBoardListReponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, boardViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
