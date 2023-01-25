package com.icia.db1.services;

import com.icia.db1.dto.CommentDTO;
import com.icia.db1.entity.BoardEntity;
import com.icia.db1.entity.CommentEntity;
import com.icia.db1.repository.BoardRepository;
import com.icia.db1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        //부모엔티티 필요
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if(optionalBoardEntity.isPresent()){
            //댓글 저장
            BoardEntity boardEntity = optionalBoardEntity.get();

            //CommentEntity 변환
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId(); // 저장한 댓글의 id 리턴

        }else{
            return null;
        }
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
//        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        //Entity List -> DTO List
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
