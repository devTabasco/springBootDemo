package com.icia.db1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment_table")
@Setter
@Getter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String commentWriter;

    @Column(nullable = false, length = 200)
    private String commentContents;

    //게시글:댓글 = 1:N(일대다 관계)
    //부모 엔티티에서 자식 엔티티를 조회할 때 함께 가져오느냐 호출할 때 가져오느냐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity; //부모 Entity 타입으로 정의


}
