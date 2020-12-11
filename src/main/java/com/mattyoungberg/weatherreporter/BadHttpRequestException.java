package com.mattyoungberg.weatherreporter;

public class BadHttpRequestException extends Exception {

    public BadHttpRequestException(String errorMessage) {
        super(errorMessage);
    }
}
