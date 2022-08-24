package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.Answer.Answer;
import com.ll.exam.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity // 아래 Question 클래스는 엔티티 클래스이다.
// 아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.
public class Question {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column(length = 200) // varchar(200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author; //작가는 유저

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL )
    private List<Answer> answerList = new ArrayList<>();

    @ManyToMany
    Set<SiteUser> voter;

    public void addAnswer(Answer answer){
        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}