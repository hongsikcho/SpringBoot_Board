package com.ll.exam.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.catalina.session.StandardSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.*;

//dev tools : 재시작할 필요없음
@Controller
public class MainController {
    static int idx;

    static {
        idx = 0;
    }

    @RequestMapping("/ssb")
    @ResponseBody // 아래 함수의 리턴값을 브라우저에 표시
    //아래 함수의 리턴값을 문자열화 해서 브라우저 응답의 바디에 담는다.
    public String index() {
        System.out.println("Hello");//서버에서 실행
        return "안녕d하2세ddd요";//먼 미래에 브라우저에서 보여짐
    }

    @RequestMapping("/page1")
    @ResponseBody
    public String showPage() {
        return """
                <input></input>
                """;
    }

    @RequestMapping("/plus")
    @ResponseBody
    public int plus(int a, int b) {
        return a + b;
    }

    @RequestMapping("/minus")
    @ResponseBody
    public int minus(int a, int b) {
        return a - b;
    }

    @RequestMapping("/increase")
    @ResponseBody
    public int increase() {
        return idx++;
    }

    @RequestMapping("/mbti/{name}")
    @ResponseBody
    public String increase(@PathVariable String name, HttpServletRequest req) {
        String rs = switch (name) {
            case "홍길동", "조홍식" -> "ENFP";
            case "최시멘" -> "ESTJ";
            default -> "모름";
        };
        return rs;
    }

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
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

    private List<Article> articles = new ArrayList<>(Arrays.asList(new Article("제목1", "내용1"),new Article("제목2", "내용2")));

    @GetMapping("/addArticle/{title}/{body}")
    @ResponseBody
    public String addArticle(@PathVariable String title, @PathVariable String body) {
        Article article = new Article(title, body);
        articles.add(article);
        return "%d번 객체가 생성되었습니다.".formatted(article.getId());
    }

    @GetMapping("/getArticle/{id}")
    @ResponseBody
    public String getArticle(@PathVariable int id) {
        for (Article article : articles) {
            if (id == article.getId()) {
                return """
                        제목 : %s
                        내용 : %s
                        """.formatted(article.getTitle(), article.getBody());
            }

        }
        return "없음 그런거";
    }

    @GetMapping("/modifyArticle/{id}/{title}/{body}")
    @ResponseBody
    public String modifyArticle(@PathVariable int id , @PathVariable String title , @PathVariable String body) {
        for (Article article : articles) {
            if (id == article.getId()) {
                    article.setTitle(title);
                    article.setBody(body);
                return """
                   수정되었음""";
            }
            //Article article = articles
            //              .stream()
            //              .filter(a -> a.getId() == id)
            //              .findFirst()
            //              .orElse(null);

        }
        return "없음 그런거";
    }

    @GetMapping("/deleteArticle/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id) // 1번
                .findFirst()
                .orElse(null);

        if (article == null) {
            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
        }

        articles.remove(article);

        return "%d번 게시물을 삭제하였습니다.".formatted(article.getId());
    }

    @GetMapping("addPersonOldWay")
    @ResponseBody
    Person addPersonOldWay(int id, int age, String name) {
        Person p = new Person(id, age, name);

        return p;
    }

    @GetMapping("addPerson")
    @ResponseBody
    Person addPerson(Person p) {
        return p;
    }

    @RequestMapping("/")
    String root(){
        return "redirect:/question/list";
    }
}

@AllArgsConstructor
@Getter
class Person {
    private int id;
    private int age;
    private String name;
};




