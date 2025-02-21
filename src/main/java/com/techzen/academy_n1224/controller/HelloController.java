package com.techzen.academy_n1224.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")

    public String sayHello(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String address) {
        return "Hello " + name + " " + address;
    }
}
