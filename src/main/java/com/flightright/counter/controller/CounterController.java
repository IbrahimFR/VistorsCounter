package com.flightright.counter.controller;

import com.flightright.counter.model.GenerateFileRequestBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CounterController {

    @ApiOperation(value = "Get Visitors count")
    @GetMapping("/count")
    long getCounts(@RequestParam("fileName") @NonNull String fileName, @RequestParam("readFaster") @NonNull boolean readFaster);

    @ApiOperation(value = "Get files names")
    @GetMapping("/files")
    List<String> getFiles();

    @ApiOperation(value = "Generate mock csv files with anonymous data")
    @PostMapping("/generate")
    ResponseEntity generateCsvFile(@RequestBody @NonNull GenerateFileRequestBody body);
}

