package com.icia.ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    //프로젝트 실행 기본주소 : http://localhost:8080/
    //기본주소에 대한 요청
    @GetMapping("/") //get방식으로 mapping하겠다.
    public String index(){
        return "index"; //index.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }
    @GetMapping("/hello") //get방식으로 mapping하겠다.
    public String hello(){
        return "hello"; //hello.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }
    @GetMapping("/hello-spring") //get방식으로 mapping하겠다.
    public String helloSpring(){
        return "hello-spring"; //hello-spring.html 을 브라우저에 출력 > 기본적으로 resources/templates안에서 찾음.
    }

    //req1요청 처리
    @GetMapping("/req1") //get방식으로 mapping하겠다.
    public String req1(@RequestParam("param1") String param1,
                       @RequestParam("param2") String param2){ //request를 받아주는 anotation @RequestParam

        System.out.println("HomeController.req1"); //soutm : method 를 보여주기
        System.out.println("param1 = " + param1 + ", param2 = " + param2); //soutp : parameter를 보여주기
        return "index";
    }
    @PostMapping("/req2") //get방식으로 mapping하겠다.
    //@RequestMapping(value = "/req2", method = RequestMethod.POST) //RequestMapping에서 한번에 받아줄수도 있다.
    public String req2(@RequestParam("value1") String value1,
                       @RequestParam("value2") String value2){ //request를 받아주는 anotation @RequestParam

        System.out.println("HomeController.req2"); //soutm : method 를 보여주기
        System.out.println("value1 = " + value1 + ", value2 = " + value2); //soutp : parameter를 보여주기
        return "index";
    }

}
