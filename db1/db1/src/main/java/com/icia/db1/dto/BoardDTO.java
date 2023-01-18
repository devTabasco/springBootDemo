package com.icia.db1.dto;

import com.icia.db1.entity.BoardEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardPass;
    private String boardContents;
    private int boardHits;

    public static BoardDTO toEntity(BoardEntity boardEntity) {
        BoardDTO boardDto = new BoardDTO();
        boardDto.setBoardContents(boardEntity.getBoardContents());
        boardDto.setBoardPass(boardEntity.getBoardPass());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setId(boardEntity.getId());
        boardDto.setBoardHits(boardEntity.getBoardHits());
        return boardDto;
    }

}
