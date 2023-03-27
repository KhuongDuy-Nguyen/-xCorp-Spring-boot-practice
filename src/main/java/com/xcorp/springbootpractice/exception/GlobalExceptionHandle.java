package com.xcorp.springbootpractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBindException(BindException e) {
    return new ErrorResponse(
        e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), "400", "Bad Request");
  }

  @ExceptionHandler(SecurityException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ErrorResponse handleSecurityException(SecurityException e) {
    return new ErrorResponse(e.getMessage(), "401", "Unauthorized");
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleNotFoundException(NotFoundException e) {
    return new ErrorResponse(e.getMessage(), "404", "Not Found");
  }

  @ExceptionHandler(NullException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleNullException(NullException e) {
    return new ErrorResponse(e.getMessage(), "400", "Null Value");
  }

  @ExceptionHandler(HasExistException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleHasExistException(HasExistException e) {
    return new ErrorResponse(e.getMessage(), "400", "Has Exist");
  }
}
