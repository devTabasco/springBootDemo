package com.example.ex2.dto;

import lombok.*;

@Getter
@Setter
@ToString
//@Data //모두 허용해주는 annotation
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
@NoArgsConstructor //기본 생성자
public class ExDTO {
    private String ex1;
    private String ex2;

}
