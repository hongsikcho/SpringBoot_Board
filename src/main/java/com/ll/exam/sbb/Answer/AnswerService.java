package com.ll.exam.sbb.Answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import com.ll.exam.sbb.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public void create(Question question, String content, SiteUser siteUser) {
        Answer ans = new Answer();
        ans.setContent(content);
        ans.setQuestion(question);
        ans.setAuthor(siteUser);
        ans.setCreateDate(LocalDateTime.now());
        question.addAnswer(ans);
        questionRepository.save(question);
    }

    public List<Answer> getAllAnswer(Question question) {
        Question q = questionRepository.findById(1L).get();

        return q.getAnswerList();
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new RuntimeException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }


    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
