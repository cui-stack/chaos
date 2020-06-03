package com.cui.tech.web.controller;

import com.cui.tech.web.ifz.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControler {
    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/home")
    public String sayHi() {
        return schedualServiceHi.sayHiFromClientOne();
    }
}
