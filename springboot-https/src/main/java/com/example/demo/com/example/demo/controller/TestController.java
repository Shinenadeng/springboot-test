package com.example.demo.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dengs
 * @Title: TestController
 * @ProjectName springboot-https
 * @Description:
 * @date 2018/10/14
 */
@Controller
public class TestController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
