package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service// 생성자주입
public class QuestionService {
    @Autowired//필드주입
    private QuestionRepository questionRepository;

    public Question findById(int id) {
        Question q1 = questionRepository.findById(2L).get();
        Question q2 = questionRepository.findById(2L    ).get();
        System.out.println(q2.getAnswerList());

        return q2;
    }


    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("수고여"));

    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void create(String subject, String content, SiteUser siteUser) {
            Question question = new Question();
            question.setSubject(subject);
            question.setContent(content);
            question.setAuthor(siteUser);
            question.setCreateDate(LocalDateTime.now());
            questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10까지 가능

        return questionRepository.findAll(pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }
}