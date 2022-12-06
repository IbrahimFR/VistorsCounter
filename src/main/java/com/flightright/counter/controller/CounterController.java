package com.flightright.counter.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CounterController {
    @GetMapping("/count")
    long getCounts();

    @GetMapping("/files")
    List<String> getFiles();
}
