package immortal.librarymanagementsystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import immortal.librarymanagementsystem.Exceptions.BookAlreadyBorrowedException;
import immortal.librarymanagementsystem.Exceptions.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyBorrowedException.class)
    public ResponseEntity<Map<String,String>> handleBookAlreadyBorrowedException(BookAlreadyBorrowedException ex){
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(Map.of("error",ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotBorrowed.class)
    public ResponseEntity<Map<String,String>> handleBookNotBorrowed(BookNotBorrowed ex){
        return new ResponseEntity<>(Map.of("error",ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleNotValid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
