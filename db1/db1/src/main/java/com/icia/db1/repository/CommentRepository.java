package com.icia.db1.repository;

import com.icia.db1.entity.BoardEntity;
import com.icia.db1.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // findById => select * from comment_table where id=?
    List<CommentEntity> findAllByBoardEntity(BoardEntity boardEntity);

    // select * from comment_table where board_id = 24 order by id desc;
    // JPA 명명법 공부 필요
    // 이름만 맞춰주면 알아서 쿼리 작성
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

}
