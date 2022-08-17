package com.ll.exam.sbb.Answer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class asd {

    @RequestMapping("/prac")
    @ResponseBody
    int 연습(@RequestParam(required = false) Integer id){
        return id;
    }
}
