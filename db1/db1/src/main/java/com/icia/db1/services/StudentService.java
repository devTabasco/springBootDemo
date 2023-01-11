package com.icia.db1.services;

import com.icia.db1.dto.StudentDTO;
import com.icia.db1.entity.StudentEntity;
import com.icia.db1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public void save(StudentDTO studentDTO){

        // repository의 매서드는 대부분 Entity 또는 pk 타입의 매개변수를 요구하고,
        // 리턴은 Entity 타입으로 주는 경우가 대부분.

        // DTO객체 -> Entity 객체
        // 팩토리 메서드(static Method), builder(.build())

        StudentEntity studentEntity = StudentEntity.toEntity(studentDTO);
        studentRepository.save(studentEntity);
    }
}
