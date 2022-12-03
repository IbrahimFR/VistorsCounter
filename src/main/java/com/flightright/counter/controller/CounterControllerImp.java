package com.flightright.counter.controller;

import com.flightright.counter.service.CountServiceImp;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterControllerImp implements CounterController {

    private final CountServiceImp countServiceImp;

    public CounterControllerImp(CountServiceImp countServiceImp) {
        this.countServiceImp = countServiceImp;
    }

    @Override
    public long getCounts() {
        return countServiceImp.countPageVisits();
    }
}
