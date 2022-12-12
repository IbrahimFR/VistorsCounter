package com.flightright.counter.controller;

import com.flightright.counter.model.GenerateFileRequestBody;
import com.flightright.counter.service.CountServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CounterControllerImp implements CounterController {

    private final CountServiceImp countServiceImp;

    public CounterControllerImp(CountServiceImp countServiceImp) {
        this.countServiceImp = countServiceImp;
    }

    @Override
    public long getCounts(String fileName, boolean readFaster) {
        return countServiceImp.countPageVisitors(fileName, readFaster);
    }

    @Override
    public List<String> getFiles() {
        return countServiceImp.getFilesNames();
    }

    @Override
    public ResponseEntity generateCsvFile(GenerateFileRequestBody body) {
        var generated = countServiceImp.generateCsvFile(body.getFileName());
        if(generated){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
