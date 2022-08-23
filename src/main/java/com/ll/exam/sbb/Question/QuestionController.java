package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.Answer.AnswerForm;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
//컨트롤러는 Repository가 있는지 몰라야 한다.
//서비스는 브라우저라는것이 이 세상에 존재하는지 몰라야 한다.
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    private final UserService userService;

    @RequestMapping("list")
    public String showList(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging" , paging);
        return "question_list";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model , @PathVariable Long id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create") //Principal객체를 사용하는 메서드에 붙힘, 로그인이 필요한 메서드를 의미
    public String questionCreate(QuestionForm questionForm) {

        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(Principal principal, Model model, @Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());

        questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }


}