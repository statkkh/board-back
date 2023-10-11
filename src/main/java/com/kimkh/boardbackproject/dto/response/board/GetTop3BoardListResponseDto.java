package com.kimkh.boardbackproject.dto.response.board;

import java.util.List;

import com.kimkh.boardbackproject.common.object.BoardListItem;
import com.kimkh.boardbackproject.dto.response.ResponseDto;
import com.kimkh.boardbackproject.entity.BoardViewEntity;

import lombok.Getter;

@Getter
public class GetTop3BoardListResponseDto extends ResponseDto{
    
    private  List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(String code ,String message , List<BoardViewEntity> boardViewEntities){
        super(code, message);
        this.top3List = BoardListItem.getList(boardViewEntities);
    }
}
