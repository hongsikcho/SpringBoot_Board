package com.ll.exam.sbb.Answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public void create(Question question , String content) {
        Answer ans = new Answer();
        ans.setContent(content);
        ans.setQuestion(question);
        ans.setCreateDate(LocalDateTime.now());
        question.addAnswer(ans);
        questionRepository.save(question);
    }

    public List<Answer> getAllAnswer(Question question) {
        Question q = questionRepository.findById(1L).get();

        return q.getAnswerList();
    }
}
