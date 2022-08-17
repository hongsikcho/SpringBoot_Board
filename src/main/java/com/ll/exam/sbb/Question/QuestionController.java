package com.ll.exam.sbb.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
//컨트롤러는 Repository가 있는지 몰라야 한다.
//서비스는 브라우저라는것이 이 세상에 존재하는지 몰라야 한다.
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("list")
    public String showList(Model model) {
        List<Question> questions = questionService.getList();
        model.addAttribute("questionList" , questions);
        return "question_list";
    }

    @RequestMapping("detail/{id}")
    public String showList(Model model , @PathVariable Integer id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("create")
    public String create() {

        return "question_form";
    }

    @PostMapping("create")
    public String questionCreate(Model model, QuestionForm questionForm){
        if(questionForm.getSubject() == null  || questionForm.getSubject().trim().length() == 0){
            model.addAttribute("error","제목을 입력해주세요");
            return "question_form";
        }

        if(questionForm.getContent() == null  || questionForm.getContent().trim().length() == 0){
            model.addAttribute("error","내용을 입력해주세요");
            return "question_form";
        }
        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";

    }


}