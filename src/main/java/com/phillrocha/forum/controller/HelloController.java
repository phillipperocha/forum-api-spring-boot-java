package com.phillrocha.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class HelloController {
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}
