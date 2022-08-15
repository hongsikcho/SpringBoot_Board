package com.ll.exam.sbb;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
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
public class QuestionRepositoryTests {
    @Autowired
    private QuestionRepository questionRepository;
    private static int lastSampleId;

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();

    }
    @Test
    void contextLoads() {
        clearData();
        createSampleData();
    }

    @Test
    public static int createSampleData(QuestionRepository questionRepository) {
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

        return q2.getId();
    }
    @Test
    private void createSampleData() {
        lastSampleId = createSampleData(questionRepository);
    }

    @Test
    public static void clearData(QuestionRepository questionRepository) {
        questionRepository.deleteAll();
        questionRepository.truncate();
    }
    private void clearData() {
        clearData(questionRepository);
    }


    @Test
    void 저장(){
        Question q1 = new Question();
        q1.setSubject("안녕하세요");
        q1.setContent("최세민입니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        assertThat(3).isEqualTo(q1.getId());
    }

    @Test
    void 삭제(){
        assertThat(questionRepository.count()).isEqualTo(lastSampleId);

        Question q = questionRepository.findById(1).get();
        questionRepository.delete(q);

        assertThat(questionRepository.count()).isEqualTo(lastSampleId -1);
    }

    @Test
    void 수정(){
        Question q = questionRepository.findById(1).get();
        q.setSubject("수정된 내용");
        questionRepository.save(q);
        
        Question q2 = questionRepository.findById(1).get();

        assertThat(q2.getSubject()).isEqualTo("수정된 내용");
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
    void findAll() {
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
    void truncate() {
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
