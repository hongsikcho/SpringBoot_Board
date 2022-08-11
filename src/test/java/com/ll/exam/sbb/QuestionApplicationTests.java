package com.ll.exam.sbb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void beforeEach(){
    }
    @Test
    void contextLoads() {
        clearData();
        createSampleData();
    }

    private void createSampleData() {
    }

    private void clearData() {
    }

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        assertThat(q1.getId()).isGreaterThan(0);
        assertThat(q2.getId()).isGreaterThan(q1.getId());
    }

    @Test
    void testJpa2() {
        // SELECT * FROM question
        List<Question> all = questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void testJpa3() {
        // SELECT * FROM question
        Question question = questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, question.getId());

    }

    @Test
    void testJpa4() {
        // SELECT * FROM question
        Question question = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
        assertEquals(1, question.getId());

    }

    @Test
    void testJpa5() {
        List<Question> list = questionRepository.findBySubjectLike("sbb%");
        Question q = list.get(0);
        assertEquals("sbb가 무엇인가요?",q.getSubject());

    }

    @Test
    void testJpa6() {
        Optional<Question> que = questionRepository.findById(1);
        assertTrue(que.isPresent());
        Question q = que.get();
        q.setSubject("ssb가 무엇인가요?");
        this.questionRepository.save(q);
    }
    @Test
    void testJpa0() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        questionRepository.disableForeignKeyChecks();
        questionRepository.truncate();
        questionRepository.enableForeignKeyChecks();
    }
}
