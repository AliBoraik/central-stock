package ru.itis.stockmarket.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.Status;
import ru.itis.stockmarket.dtos.ValidationError;
import ru.itis.stockmarket.exceptions.CustomServerErrorException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA
 * Date: 14.05.2022
 * Time: 11:22 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // map all validation errors
        HashMap<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        // get the request path
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        // return our ValidationError class
        ValidationError err = ValidationError.builder()
                .errors(errors)
                .timestamp(System.currentTimeMillis())
                .description("Bad Request")
                .path(path)
                .status(Status.failure)
                .statusCode(status.value())
                .build();

        return new ResponseEntity<>(err, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       return this.handleBindException(ex,headers,status,request);
    }

    @ExceptionHandler(CustomServerErrorException.class)
    ResponseEntity<ValidationError> handleCustomServerErrorException(CustomServerErrorException ex) {
        ValidationError err = ValidationError.builder()
                .description(ex.getStatusText())
                .status(Status.failure)
                .timestamp(System.currentTimeMillis())
                .statusCode(ex.getStatusCode().value())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(err);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println(ex);
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String _body = Objects.isNull(body) ? ex.getLocalizedMessage():body.toString();
        ValidationError err = ValidationError.builder()
                .description(_body)
                .statusCode(status.value())
                .status(Status.failure)
                .timestamp(System.currentTimeMillis())
                .path(path)
                .build();
        return ResponseEntity.status(status).headers(headers).body(err);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<GeneralMessage<?>> handleAllErrors(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(GeneralMessage.builder()
                        .status(Status.failure)
                        .description("Something went wrong")
                        .data(e.getMessage())
                        .build());
    }
}
