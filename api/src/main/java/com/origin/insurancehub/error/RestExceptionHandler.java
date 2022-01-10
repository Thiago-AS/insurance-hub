package com.origin.insurancehub.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        final RestErrorResponse error =
                new RestErrorResponse(LocalDateTime.now().atZone(ZoneId.systemDefault()),
                        status.value(), status.getReasonPhrase(),
                        ((ServletWebRequest) request).getRequest().getRequestURI());
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getViolations().add(RestViolation.builder()
                    .fieldName(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build());
        }
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.warn(exception.toString());
        final Object response =
                Objects.isNull(body)
                        ? new RestErrorResponse(LocalDateTime.now().atZone(ZoneId.systemDefault()),
                        status.value(), status.getReasonPhrase(),
                        ((ServletWebRequest) request).getRequest().getRequestURI()) : body;
        return super.handleExceptionInternal(exception, response, headers, status, request);
    }
}
