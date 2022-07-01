package ru.itis.stockmarket.exceptions;

import org.springframework.http.HttpStatus;


public class AlreadyExistsException extends CustomServerErrorException {
    public AlreadyExistsException(String statusText) {
        super(HttpStatus.CONFLICT, statusText);
    }
}
