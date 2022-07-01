package ru.itis.stockmarket.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA
 * Date: 14.05.2022
 * Time: 4:52 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public class NotFoundException extends CustomServerErrorException {
    public NotFoundException(String statusText) {
        super(HttpStatus.NOT_FOUND, statusText);
    }
}
