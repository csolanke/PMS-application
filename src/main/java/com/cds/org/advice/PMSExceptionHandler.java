package com.cds.org.advice;


import com.cds.org.dto.ErrorMessage;
import com.cds.org.exceptions.ClientDetailsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PMSExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidRequestArguments(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();
         ex.getBindingResult().getFieldErrors()
                 .forEach(err-> errorMap.put(err.getField(),err.getDefaultMessage()));

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientDetailsNotFoundException.class)
    public  ErrorMessage handleResourceNotFoundException(ClientDetailsNotFoundException ex){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setError("Not Found");
        errorMessage.setStatus(404);
        errorMessage.setException("com.cds.org.exceptions.ClientDetailsNotFoundException");
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setTimestamp(LocalDateTime.now());

        return errorMessage;

    }

}
