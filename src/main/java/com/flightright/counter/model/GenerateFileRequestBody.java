package com.flightright.counter.model;

/**
 * Request body for generate csv file
 */
public class GenerateFileRequestBody {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
