package com.icia.db1.controller;

import com.icia.db1.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("/save")
//    public @ResponseBody String save(@RequestBody CommentDTO commentDTO){
    public ResponseEntity save(@RequestBody CommentDTO commentDTO){
        System.out.println("commentDTO = " + commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK); //return해줄 데이터 , httpStatus Code
    }
}
