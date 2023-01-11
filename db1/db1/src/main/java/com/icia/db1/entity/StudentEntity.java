package com.icia.db1.entity;

import com.icia.db1.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student_table")
@Setter
@Getter
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

    // DTO -> ENTITY RETURN
    public static StudentEntity toEntity(StudentDTO studentDTO) {
        //외부에서 studentEntity에 직접접근하지말고, 내부에서만 접근해서 사용하자...
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentName(studentDTO.getStudentName());
        studentEntity.setStudentAge(studentDTO.getStudentAge());
        return studentEntity;
    }
}

