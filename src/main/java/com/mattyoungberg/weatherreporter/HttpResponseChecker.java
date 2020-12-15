package com.mattyoungberg.weatherreporter;

import java.net.http.HttpResponse;

public class HttpResponseChecker {

    public static void throwIfNot2xx(HttpResponse<String> response) throws BadHttpRequestException {
        int statusCode = response.statusCode();
        if(statusCode / 100 != 2) {
            throw new BadHttpRequestException("Request to " + response.uri() + " returned an unsuccessful status " + statusCode + ".");
        }
    }
}
