package com.icia.db1;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.services.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.icia.db1.controller.BoardController;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;

    // 테스트 코드: 테스트를 위한 시나리오 등을 만들어서 모든 과정이 코드로 자동으로 수행되고,
    // 테스트 결과만 확인

    // 글 작성 테스트
    // 새로운 글을 등록 : BoardDTO
    // 새로운 게시글의 id값을 가져와서
    // 그 id값으로 조회를 한 뒤 : findById
    // 새로운 글에서 작성한 작성자 값과 조회한 글의 작성자 값이 일치하면,
    // 테스트 성공, 일치하지 않으면 테스트 실패

    //junit : java에서 제공하는 테스트용 pkg
    @Test
    @Transactional
    @Rollback(value = true) //value=false로 하면, 전부 commit되어 DB에 기록이 남음.
    public void saveTest(){
        //새로운 글을 등록하기 위한 boardDTO객체
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("테스트제목111");
        boardDTO.setBoardWriter("테스트 작성자111");
        boardDTO.setBoardContents("테스트 내용111");
        boardDTO.setBoardPass("테스트비밀번호111");

        //위에서 만든 객체를 저장하기 위해 메서드 호출 & 받아온 id로 db조회
        BoardDTO dto = boardService.findById(boardService.save(boardDTO));

        //boardDTO 객체의 작성자 값과 dto작성자 값이 일치하는지 확인
//        System.out.println(boardDTO.getBoardWriter().equals(dto.getBoardWriter()));

        //assertJ import
        //static Method
        //성공이 된다면, 초록색 체크가 뜨게 됨.
        //테스트코드 짜봄...
        Assertions.assertThat(boardDTO.getBoardWriter()).isEqualTo(dto.getBoardWriter());
    }

    private BoardDTO newBoard(int i){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("제목"+i);
        boardDTO.setBoardWriter("작성자"+i);
        boardDTO.setBoardPass("비밀번호"+i);
        boardDTO.setBoardContents("내용"+i);
        return boardDTO;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveList(){

        for(int i=1;i<=20;i++){
            boardService.save(newBoard(i));
        }

    }



}
