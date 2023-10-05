package com.kimkh.boardbackproject.common.object;

import java.util.ArrayList;
import java.util.List;

import com.kimkh.boardbackproject.repository.resultSet.CommentListResultSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentListItem {
    
    private String nickname;
    private String profileImage;
    private String writeDatetime;
    private String content;
 
    public CommentListItem( CommentListResultSet resultSet){
        this.nickname = resultSet.getNickname();
        this.content = resultSet.getContent();
        this.profileImage = resultSet.getProfileImage();
        this.writeDatetime = resultSet.getWriteDatetime();

    }

    public static List<CommentListItem> getList(List<CommentListResultSet> resultSets){
        List<CommentListItem> list = new ArrayList<>();
        for(CommentListResultSet resultSet : resultSets){
            CommentListItem commentListItem = new CommentListItem(resultSet);
            list.add( commentListItem);
        }
        return list;

    }
}
