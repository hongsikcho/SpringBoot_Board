package com.ll.exam.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String s);

    Question findBySubjectAndContent(String s, String s1);

    @Query(value = "SET FOREIGN_KEY_CHECKS = 0;" ,nativeQuery = true)
    Question truncate1();

    @Query(value = "truncate Question;" ,nativeQuery = true)
    Question truncate2();

    @Query(value = "SET FOREIGN_KEY_CHECKS = 1;" ,nativeQuery = true)
    Question truncate3();

    List<Question> findBySubjectLike(String s);
}