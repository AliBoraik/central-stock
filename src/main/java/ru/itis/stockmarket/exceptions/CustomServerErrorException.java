package ru.itis.stockmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;


public class CustomServerErrorException extends HttpServerErrorException {
    public CustomServerErrorException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
