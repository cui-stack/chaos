package com.cui.tech.web.service;

import com.cui.tech.web.ifz.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne() {
        return "sorry ooo222";
    }
}
