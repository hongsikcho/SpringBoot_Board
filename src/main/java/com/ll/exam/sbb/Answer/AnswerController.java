package com.ll.exam.sbb.Answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable int id, String content) {
        Question question = questionService.getQuestion(id);

        answerService.create(question,content);
        // 답변 등록 끝

        return "redirect:/question/detail/%d".formatted(id);
    }
}
