package br.com.caiobruno.restspring.exceptions.handler;

import br.com.caiobruno.restspring.exceptions.ExceptionsResponse;
import br.com.caiobruno.restspring.exceptions.InvalidJwtAuthenticationException;
import br.com.caiobruno.restspring.exceptions.RequiredObjectIsNullException;
import br.com.caiobruno.restspring.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResposeEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionsResponse> handleAllException(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(), ex.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionsResponse> ResourceNotFoundException(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(), ex.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionsResponse> handleBadRequestException(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(), ex.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionsResponse> handleInvalidJwtAuthenticationException(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(), ex.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.FORBIDDEN);
    }


}
