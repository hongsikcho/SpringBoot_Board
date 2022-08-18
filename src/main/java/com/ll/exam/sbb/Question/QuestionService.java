package com.ll.exam.sbb.Question;

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
import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자주입
public class QuestionService {
    @Autowired//필드주입
    private QuestionRepository questionRepository;

    public Question findById(int id) {
        Question q1 = questionRepository.findById(2).get();
        Question q2 = questionRepository.findById(2).get();
        System.out.println(q2.getAnswerList());

        return q2;
    }


    public Question getQuestion(int id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("수고여"));

    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void create(String subject, String content) {
            Question question = new Question();
            question.setSubject(subject);
            question.setContent(content);
            question.setCreateDate(LocalDateTime.now());
            questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10까지 가능

        return questionRepository.findAll(pageable);
    }
}