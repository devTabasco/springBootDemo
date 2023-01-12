package com.icia.db1.services;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.dto.StudentDTO;
import com.icia.db1.entity.BoardEntity;
import com.icia.db1.entity.StudentEntity;
import com.icia.db1.repository.BoardRepository;
import com.icia.db1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO){

        // repository의 매서드는 대부분 Entity 또는 pk 타입의 매개변수를 요구하고,
        // 리턴은 Entity 타입으로 주는 경우가 대부분.

        // DTO객체 -> Entity 객체
        // 팩토리 메서드(static Method), builder(.build())

        BoardEntity boardEntity = BoardEntity.toEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll(){
        List<BoardEntity> boardEntityList = boardRepository.findAll(); // select * from board_table;
        //List<BoardEntity> -> List<BoardDTO>
        List<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();

        for(BoardEntity boardEntity : boardEntityList){
            //Entity 객체를 DTO객체로 변환 후, DTOList 에 담기.
            BoardDTO boardDTO = BoardDTO.toEntity(boardEntity);
            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        // update board_table set boardHits = boardHits+1 where id = ?
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        // 자바 optional : NPE 방지를 위해 객체를 한 번 더 포장.
        if(boardEntityOptional.isPresent()){
            // 조회 결과가 있다.
            //get() : optinal 객체에 들어있는 객체를 가져옴.
            BoardEntity boardEntity = boardEntityOptional.get();
            BoardDTO boardDTO = BoardDTO.toEntity(boardEntity);
            return boardDTO;
        } else {
            // 조회 결과가 없다.
            return null;
        }
    }
}
