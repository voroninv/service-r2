package com.base.servicer2.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.base.servicer2.constants.Constants.BAD_REQUEST;
import static com.base.servicer2.constants.Constants.BAD_SQL_GRAMMAR_EXCEPTION;
import static com.base.servicer2.constants.Constants.INTERNAL_SERVER_ERROR;
import static com.base.servicer2.constants.Constants.NOT_FOUND;
import static com.base.servicer2.constants.Constants.SQL_EXCEPTION;
import static com.base.servicer2.constants.Constants.SQL_EXCEPTION_MESSAGE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("error", INTERNAL_SERVER_ERROR);
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("error", NOT_FOUND);
        body.put("message", ex.getMessage());
        body.put("timestamp", ex.getTimestamp());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        logger.error(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("error", BAD_REQUEST);
        body.put("message", ex.getMessage());
        body.put("timestamp", ex.getTimestamp());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException ex) {
        logger.error(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("error", SQL_EXCEPTION);
        body.put("message", SQL_EXCEPTION_MESSAGE);
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<Object> handleBadSqlGrammarException(BadSqlGrammarException ex) {
        logger.error(ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("error", BAD_SQL_GRAMMAR_EXCEPTION);
        body.put("message", SQL_EXCEPTION_MESSAGE);
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
