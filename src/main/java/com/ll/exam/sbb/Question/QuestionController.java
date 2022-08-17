package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.Answer.AnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/question")
//컨트롤러는 Repository가 있는지 몰라야 한다.
//서비스는 브라우저라는것이 이 세상에 존재하는지 몰라야 한다.
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("list")
    public String showList(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging" , paging);
        return "question_list";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model , @PathVariable Integer id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }


}