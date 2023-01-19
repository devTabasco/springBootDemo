package com.icia.db1.entity;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String boardWriter;

    //board_title: varchar(50), not null
    @Column(nullable = false, length = 50)
    private String boardTitle;

    //board_pass: varchar(20), not null
    @Column(nullable = false, length = 20)
    private String boardPass;

    //board_contents: varchar(500)
    @Column
    private String boardContents;

    //board_hits: int
    @Column
    private int boardHits;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE,
    orphanRemoval = true, fetch = FetchType.LAZY) //부모 삭제시 자식도 삭제
    private List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();



    // DTO -> ENTITY RETURN
    public static BoardEntity toEntity(BoardDTO boardDTO) {
        //외부에서 studentEntity에 직접접근하지말고, 내부에서만 접근해서 사용하자...
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        //update 시에 모든 컬럼을 다 set해주어야 함.
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }


}
