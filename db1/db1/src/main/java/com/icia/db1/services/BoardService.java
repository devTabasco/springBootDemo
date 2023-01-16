package com.icia.db1.services;

import com.icia.db1.dto.BoardDTO;
import com.icia.db1.entity.BoardEntity;
import com.icia.db1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public BoardDTO update(BoardDTO boardDTO){
        //JPA의 특징 : 저장, 수정 => save Method 사용 PK유무로 확인

        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO); //수정용 Entity 변환
        boardRepository.save(boardEntity); //수정 요청

//        BoardDTO boardDTO1 = this.findById(boardDTO.getId());
        return findById(boardDTO.getId());
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

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    /*
    * 전체 글 갯수 35
    * 한 페이지 10개
    * 필요 페이지 4개
    *
    * Mybatis는 계산 직접 해야함.
    *
    * */
    public Page<BoardDTO> paging(Pageable pageable) {
        //getPageNumber : 사용자가 요청한 페이지번호
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; //한 페이지에 보여줄 글 갯수

        Page<BoardEntity> boardEntities =
                // page : 몇페이지 볼래? 0 이 시작 따라서 무조건 1씩 빼줘야함.
                // pageLimit : 한 페이지에 몇개씩 볼래?
                //
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호(JPA기준)
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        return null;
    }
}
