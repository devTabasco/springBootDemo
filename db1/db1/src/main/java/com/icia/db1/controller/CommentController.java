package com.icia.db1.controller;

import com.icia.db1.dto.CommentDTO;
import com.icia.db1.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
//    public @ResponseBody String save(@RequestBody CommentDTO commentDTO){
    public ResponseEntity save(@RequestBody CommentDTO commentDTO){
        System.out.println("commentDTO = " + commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if(saveResult != null) {
            // 댓글이 작성되면 내가 쓴 댓글이 포함된 목록이 화면으로 넘어감
            // 댓글 목록 조회
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());

            return new ResponseEntity<>(commentDTOList, HttpStatus.OK); //return해줄 데이터 , httpStatus Code
        }else{
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
