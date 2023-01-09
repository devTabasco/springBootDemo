package com.icia.db1.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_table")
public class StudentEntity {
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 세팅
    private Long id;

    //학생이름 저장 컬럼(student_name)
    @Column(nullable = false, length = 30) //제약조건 추가
    private String studentName;

    //학생 나이
    @Column
    private int studentAge;
}

