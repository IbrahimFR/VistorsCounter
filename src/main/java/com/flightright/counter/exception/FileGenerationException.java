package com.flightright.counter.exception;

/**
 * FileGenerationException is exception throws when there is error while generated the mock csv file.
 */
public class FileGenerationException extends RuntimeException {
    public FileGenerationException(String message) {
        super(message);
    }
}
