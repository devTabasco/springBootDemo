package com.icia.db1.entity;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
board_table
id(pk): bigint, auto_increment
board_writer: varchar(20), not null
board_title: varchar(50), not null
board_pass: varchar(20), not null
board_contents: varchar(500)
board_hits: int
* */
@Entity
@Table(name = "board_table")
@Setter
@Getter
public class BoardEntity {
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 세팅
    private Long id;

    @Column(nullable = false, length = 20) //제약조건 추가
    private String board_writer;

    //board_title: varchar(50), not null
    @Column(nullable = false, length = 50)
    private String board_title;

    //board_pass: varchar(20), not null
    @Column(nullable = false, length = 20)
    private String board_pass;

    //board_contents: varchar(500)
    @Column
    private String board_contents;

    //board_hits: int
    @Column
    private int board_hits;

    // DTO -> ENTITY RETURN
    public static BoardEntity toEntity(BoardDTO boardDTO) {
        //외부에서 studentEntity에 직접접근하지말고, 내부에서만 접근해서 사용하자...
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoard_writer(boardDTO.getBoardWriter());
        boardEntity.setBoard_title(boardDTO.getBoardTitle());
        boardEntity.setBoard_pass(boardDTO.getBoardPass());
        boardEntity.setBoard_contents(boardDTO.getBoardContents());
        boardEntity.setBoard_hits(boardDTO.getBoardHits());
        return boardEntity;
    }
}
