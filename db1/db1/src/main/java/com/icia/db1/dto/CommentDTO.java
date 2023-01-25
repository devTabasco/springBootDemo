package com.icia.db1.dto;

import com.icia.db1.entity.CommentEntity;
import lombok.Data;

import javax.xml.stream.events.Comment;

@Data
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;


    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId()); //부모의 id값을 가져올 수 있음.
        //commentDTO.setBoardId(boardId); //부모의 id값을 가져올 수 있음. //이 함수를 사용하는 메소드에 transational 을 호출해주어야함.
        return commentDTO;
    }
}
