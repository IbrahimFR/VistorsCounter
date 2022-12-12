package com.flightright.counter.exception;

/**
 * FileNotFoundException is handled exception throws when the entered file is not in the resources folder
 */
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String message) {
        super(message);
    }
}
