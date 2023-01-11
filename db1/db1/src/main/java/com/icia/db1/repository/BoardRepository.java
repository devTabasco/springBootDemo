package com.icia.db1.repository;

import com.icia.db1.entity.BoardEntity;
import com.icia.db1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
