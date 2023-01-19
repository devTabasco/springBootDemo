package com.icia.db1.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;


}
