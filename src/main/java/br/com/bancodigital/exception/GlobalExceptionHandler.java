package br.com.bancodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> customException(CustomException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException ex) {
        String friendlyMessage = "Resource not found.";
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, friendlyMessage);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> resourceAlreadyExists(ResourceAlreadyExistsException ex) {
        String friendlyMessage = "Resource already exist.";
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, friendlyMessage);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> unauthorizedException(UnauthorizedException ex) {
        String friendlyMessage = "Unauthorized to access.";
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, friendlyMessage);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> resourceNotFound(MethodArgumentNotValidException ex) {
        String friendlyMessage = "Missing JSON information.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage);
        apiError.addBindingResult(ex.getBindingResult());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
