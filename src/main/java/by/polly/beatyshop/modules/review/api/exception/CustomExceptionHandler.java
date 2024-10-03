package by.polly.beatyshop.modules.review.api.exception;

import by.polly.beatyshop.modules.review.api.dto.ExceptionResponse;
import by.polly.beatyshop.modules.review.api.exception.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        record ErrorField(String field, Object message) {}
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorField(
                        fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(toMap(ErrorField::field, ErrorField::message));
        return createResponse(ex, HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception exception) {
        log.error(exception.getMessage());
        return switch (exception) {
            case EntityNotFoundException e -> createResponse(e, HttpStatus.NOT_FOUND, new HashMap<>());
            default -> createResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<>());
        };
    }

    private ResponseEntity<Object> createResponse(Exception e, HttpStatus status, Map<String, Object> errors) {
        return ResponseEntity.status(status).body(new ExceptionResponse(errors, e.getMessage(), status));
    }

}
