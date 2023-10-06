package com.kimkh.boardbackproject.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDto {
    // 문자열만 지정
    @NotBlank
    private String content;
         
}
