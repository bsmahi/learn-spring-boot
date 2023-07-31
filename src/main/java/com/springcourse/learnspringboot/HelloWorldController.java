package com.springcourse.learnspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody = @RestController
public class HelloWorldController {

    @GetMapping("/")
    public String helloWorld() {
        return "Welcome to Spring Boot Course!";
    }
}
