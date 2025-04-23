package com.codele.demo.CONTROLLER;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("public-endpoint")
    public String sayhi(){
        return "hi";
    }

}
