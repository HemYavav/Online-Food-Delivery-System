package com.esewa.shared.exception.exceptionhandler;

import com.esewa.shared.exception.exceptionhandler.exceptioncollection.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalHttpErrorHandlerException {

    @ExceptionHandler(UserAlreadyDeletedException.class)
    public ResponseEntity<Object> userAlreadyDeletedException(UserAlreadyDeletedException e) {
        log.error("UserAlreadyDeletedException: " + e.getMessage());
        return new ResponseEntity<>("User is Already Deleted From This System ", NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<Object> invalidUserIdExceptionHandler(InvalidUserIdException e) {
        log.error("InvalidUserIdException: " + e.getMessage());
        return new ResponseEntity<>("Please input correct email amd password", NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Object> emailAlreadyExistHandler(EmailAlreadyExistException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Email already registered...", CONFLICT);
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Object> emailNotFoundHandler(EmailNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Email with given ID doest not exist", NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<Object> userAlreadyExistHandler(UserAlreadyRegisteredException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("User already registered...", CONFLICT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("User Not found...", CONFLICT);
    }


    @ExceptionHandler(UsernamePasswordNotMatchException.class)
    public ResponseEntity<Object> UsernamePasswordNotMatchExceptionHandler(UsernamePasswordNotMatchException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Username And Password Not Matched...", BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserPasswordFoundException.class)
    public ResponseEntity<Object> emailNotFoundHandler(InvalidUserPasswordFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("InvalidUserPasswordFoundException: " + "Invalid entered Password. Your Password is not Matched", BAD_REQUEST);
    }

//   Exception for Recipe Service



    @ExceptionHandler(RecipeNotExistException.class)
    public ResponseEntity<Object> recipeNotExistExceptionHandler(RecipeNotExistException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("There is no any Recipe present in List...", BAD_REQUEST);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<Object> recipeNotFoundExceptionHandler(RecipeNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Recipe Not Found...", BAD_REQUEST);
    }




    @ExceptionHandler(RecipeAlreadyFoundByUserEmail.class)
    public ResponseEntity<Object> recipeAlreadyFoundByUserEmailExceptionHandler(RecipeAlreadyFoundByUserEmail e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Recipe is already found by user email...", BAD_REQUEST);
    }

}
