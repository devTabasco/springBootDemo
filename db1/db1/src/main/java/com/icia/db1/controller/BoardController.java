package com.icia.db1.controller;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.dto.StudentDTO;
import com.icia.db1.services.BoardService;
import com.icia.db1.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "update";
    }

    //http request method
    //get, host, put(patch), delete

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        //RestAPI의 정석은 deleteMapping 이용
        boardService.delete(id);
        //서버에 삭제 후 재요청해야하기 때문에 redirect
        return "redirect:/board/";
    }

    @PostMapping("/board/update") //수정처리
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board",board);
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

    //pageable : 스프링에서 제공하는 객체
    @GetMapping("/board/paging")
    public String paging(@PageableDefault(page=1) Pageable pageable, Model model){
        //Page 객체(Spring 지원)
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }

}
