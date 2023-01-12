package com.icia.db1.controller;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.dto.StudentDTO;
import com.icia.db1.services.BoardService;
import com.icia.db1.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// MVC(Model View Controller)
/* Java Class
*  View - Controller - Service - Repository - DB
*
* */

@Controller
@RequiredArgsConstructor //final이 붙은 필드로만 매개변수로 하는 생성자를 만듦.
public class BoardController {
    private final BoardService boardService; //생성자 주입
    // 학생등록 페이지 출력
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @GetMapping("/board/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);

        return "list";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        boardService.updateHits(id); //조회수 증가
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
       return "detail";
    }

    //상세조회(/board?id=3)
//    @GetMapping("/board")
//    public String findById(@RequestParam("id") Long id, Model model){
//
//    }

    // Save.html에서 입력한 학생 정보 가저오기
    @PostMapping("/dataSave")
//    public String save(@RequestParam("studentName") String studentName,
//                       @RequestParam("studentAge") int studentAge){
//        return null;
//    }
    //입력값을 DTO 객체로 바로 받기
    public String save(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);
        //studentService Class의 save 매서드를 호출
        boardService.save(boardDTO);

        //redirect 방식으로 목록 출력을 위한 url요청
        return "redirect:/board/";
    }
}
