package com.rest.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Democontroller {
    @GetMapping("/a")
    public String abc()
    {
        return "Hello world";
    }
    @GetMapping("/b")
    public String ab()
    {
        return "Hello Indhu";
    }
    @GetMapping("/abcd")
    public String getStudentName(@RequestParam String studentname,@RequestParam int studentage)
    {
        return "GOOD MORINING"+studentname+studentage ;
    }
    @GetMapping("/{studentname}")
    public String getstudentname(@PathVariable String studentname)
    {
        return "welcome " + studentname;
    }
}
