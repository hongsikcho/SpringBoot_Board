package com.ll.exam.sbb.Question;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class QuestionForm {
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max = 200 , message="제목을 200자 이하로 입력해주세요.")
    private String subject;
    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}
