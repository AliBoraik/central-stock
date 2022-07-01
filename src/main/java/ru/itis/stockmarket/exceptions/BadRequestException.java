package ru.itis.stockmarket.exceptions;

import org.springframework.http.HttpStatus;


public class BadRequestException extends CustomServerErrorException {
    public BadRequestException(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }
}
