package com.ll.exam.sbb;

import org.apache.catalina.session.StandardSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//dev tools : 재시작할 필요없음
@Controller
public class MainController {
    static int idx;
    static{
        idx = 0;
    }
    @RequestMapping("/ssb")
    @ResponseBody // 아래 함수의 리턴값을 브라우저에 표시
    //아래 함수의 리턴값을 문자열화 해서 브라우저 응답의 바디에 담는다.
    public String index(){
        System.out.println("Hello");//서버에서 실행
        return "안녕d하2세ddd요";//먼 미래에 브라우저에서 보여짐
    }

    @RequestMapping("/page1")
    @ResponseBody
    public String showPage(){
        return """
                <input></input>
                """;
    }

    @RequestMapping("/plus")
    @ResponseBody
    public int plus(int a , int b) {
        return a+b;
    }

    @RequestMapping("/minus")
    @ResponseBody
    public int minus(int a , int b) {
        return a-b;
    }

    @RequestMapping("/increase")
    @ResponseBody
    public int increase() {
        return idx++;
    }
    
    @RequestMapping("/mbti/{name}")
    @ResponseBody
    public String increase(@PathVariable String name, HttpServletRequest req) {
        String rs = switch(name){
            case "홍길동" , "조홍식" -> "ENFP";
            case "최시멘" -> "ESTJ";
            default -> "모름";
        };
        return rs;
    }

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession( @PathVariable String name,  @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession();

        session.setAttribute(name, value);

        return "세션변수 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);

        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }

    @GetMapping("/addArticle/{title}/{body}")
    @ResponseBody
    public String addArticle(@PathVariable String title, @PathVariable String body) {
        Article article = new Article(title, body);
        return "%d번 객체가 생성되었습니다.".formatted();
    }

    }


