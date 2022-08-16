package com.ll.exam.sbb.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("수고여"));

    }

    public void save(Question question) {
        questionRepository.save(question);
    }
}