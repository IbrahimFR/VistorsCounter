package com.flightright.counter.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface CounterController {
    @GetMapping("/count")
    long getCounts();
}
