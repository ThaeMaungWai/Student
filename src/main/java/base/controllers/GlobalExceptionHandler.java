package base.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public String handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return "Error: Duplicate entry or constraint violation occurred.";
    }

    @ExceptionHandler(org.eclipse.persistence.exceptions.DatabaseException.class)
    @ResponseBody
    public String handleDatabaseException(org.eclipse.persistence.exceptions.DatabaseException e) {
        return "Error: Database exception occurred.";
    }
}
