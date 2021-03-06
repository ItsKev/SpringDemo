package ch.peax.demo.exception;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        "Internal Server Error",
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
    log.warn(exception.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
    log.warn(exception.getMessage(), exception);
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        exception.getMessage(),
        exception.getValue().toString());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        "Validation Failed",
        exception.getBindingResult().toString());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
