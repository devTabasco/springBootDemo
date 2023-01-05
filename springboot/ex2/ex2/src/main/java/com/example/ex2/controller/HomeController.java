package com.example.ex2.controller;

import com.example.ex2.dto.ExDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    //프로젝트 실행 기본주소 : http://localhost:8080/

    //기본주소에 대한 요청
    @GetMapping("/") //get방식으로 mapping하겠다.
    public String index(){
        return "index"; //index.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }
    @GetMapping("/page1") //get방식으로 mapping하겠다.
    public String page1(){
        return "page1"; //hello.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }
    @GetMapping("/page2") //get방식으로 mapping하겠다.
    public String helloSpring(){
        return "page2"; //hello-spring.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }
    @GetMapping("/page3") //String 변수 하나 정의하고, 그 변수를 page3.html에 가져가서 출력
    public String page3(Model model){
        String val1 = "저는 String 입니다.";
        model.addAttribute("str1",val1);
        return "page3";
    }
    @GetMapping("/page4") //Class 객체를 page4.html에 가져가서 출력
    public String page4(Model model){
        ExDTO exDTO = new ExDTO();
        exDTO.setEx1("안녕하세요.");
        exDTO.setEx2("반갑습니다.");
        model.addAttribute("dto",exDTO);
        return "page4";
    }
    @GetMapping("/page5") //List 객체를 page5.html에 가져가서 출력
    public String page5(Model model){
        List<ExDTO> exDTOList = new ArrayList<ExDTO>();
        exDTOList.add(new ExDTO("이제 곧","끝납니다."));
        exDTOList.add(new ExDTO("조금만 더 ","힘내요."));
        model.addAttribute("list",exDTOList);
        return "page5";
    }

    //request 요청 처리

    @GetMapping("/input1") //get방식으로 mapping하겠다.
    public String req1(@RequestParam("data1") String data1,
                       @RequestParam("data2") String data2){ //request를 받아주는 anotation @RequestParam

        System.out.println("data1 = " + data1 + ", data2 = " + data2);
        return "index";
    }
    @PostMapping("/input2") //post방식으로 mapping하겠다.
    //@RequestMapping(value = "/input2", method = RequestMethod.POST) //RequestMapping에서 한번에 받아줄수도 있다.
    public String req2(@RequestParam("var1") String var1,
                       @RequestParam("var2") String var2){ //request를 받아주는 anotation @RequestParam

        System.out.println("var1 = " + var1 + ", var2 = " + var2);
        return "index";
    }

}
