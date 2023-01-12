package com.icia.db1.controller;

import com.icia.db1.dto.StudentDTO;
import com.icia.db1.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// MVC(Model View Controller)
/* Java Class
*  View - Controller - Service - Repository - DB
*
* */

//@Controller
//@RequiredArgsConstructor //final이 붙은 필드로만 매개변수로 하는 생성자를 만듦.
//public class StudentController {
//    private final StudentService studentService; //생성자 주입
//    // 학생등록 페이지 출력
//    @GetMapping("/save")
//    public String saveForm(){
//        return "save";
//    }
//    // Save.html에서 입력한 학생 정보 가저오기
//    @PostMapping("/save")
////    public String save(@RequestParam("studentName") String studentName,
////                       @RequestParam("studentAge") int studentAge){
////        return null;
////    }
//    //입력값을 DTO 객체로 바로 받기
//    public String save(@ModelAttribute StudentDTO studentDTO){
//        System.out.println("studentDTO = " + studentDTO);
//        //studentService Class의 save 매서드를 호출
//        studentService.save(studentDTO);
//
//        return "index";
//    }
//}
