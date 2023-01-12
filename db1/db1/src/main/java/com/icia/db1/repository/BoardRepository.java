package com.icia.db1.repository;

import com.icia.db1.entity.BoardEntity;
import com.icia.db1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
        //jpgl 방식
        //java의 변수명을 써야하고, 테이블은 별칭을 꼭 써야함.
        //:id가 @param과 매칭
    void updateHits(@Param("id") Long id);
}
