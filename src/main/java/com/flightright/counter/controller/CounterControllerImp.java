package com.flightright.counter.controller;

import com.flightright.counter.service.CountServiceImp;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public List<String> getFiles() {
        return countServiceImp.getFilesNames();
    }
}
