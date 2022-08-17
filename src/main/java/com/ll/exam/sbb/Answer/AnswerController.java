package com.ll.exam.sbb.Answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable int id, @Valid AnswerForm answerForm, BindingResult bindingResult) {

        Question question = this.questionService.getQuestion(id);

        if ( bindingResult.hasErrors() ) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        // 답변 등록 시작
        answerService.create(question, answerForm.getContent());
        // 답변 등록 끝

        return "redirect:/question/detail/%d".formatted(id);
    }
}