package com.ll.exam.sbb;

import com.ll.exam.sbb.Answer.Answer;
import com.ll.exam.sbb.Answer.AnswerRepository;
import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class AnswerApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();

    }
    @Test
    private void createSampleData() {
        QuestionRepositoryTests.createSampleData(questionRepository);
        Question q = questionRepository.findById(1L).get();

        Answer a1 = new Answer();
        a1.setContent("sbb는 질문답변 게시판 입니다.");
        a1.setCreateDate(LocalDateTime.now());
        q.addAnswer(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링부트관련 내용을 다룹니다.");
        a2.setCreateDate(LocalDateTime.now());
        q.addAnswer(a2);

        questionRepository.save(q);

    }
    @Test
    private void clearData() {
        QuestionRepositoryTests.clearData(questionRepository);
        answerRepository.deleteAll();
        answerRepository.truncate();
    }

    @Test
    @Transactional
    @Rollback(false)
    void 저장(){
        Question q = questionRepository.findById(1L).get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성되용");
        a.setCreateDate(LocalDateTime.now());
        q.addAnswer(a);
        questionRepository.save(q);
        assertThat(answerRepository.count()).isEqualTo(3);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 조회() {
        Answer a = this.answerRepository.findById(1L).get();
        assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 답변_관련_질문_조회() { //a에서 findById를 실행하면 question에 대한 정보까지 가져온다.
        Answer a = this.answerRepository.findById(1L).get();
        Question q = a.getQuestion();
        assertThat(q.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 질문_관련_답변_조회() {
        Question q = questionRepository.findById(1L).get();

        List<Answer> li = q.getAnswerList();

        assertThat(li.size()).isEqualTo(2);
    }
}
