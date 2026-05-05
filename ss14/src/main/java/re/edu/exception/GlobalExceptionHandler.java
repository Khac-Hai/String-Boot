package re.edu.exception;

import re.edu.validator.BadCredentialsExceptionCustom;
import re.edu.validator.JwtExceptionCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtExceptionCustom.class)
    public ResponseEntity<?> handleJwtException(JwtExceptionCustom ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse("TOKEN_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsExceptionCustom.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsExceptionCustom ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    private Map<String, Object> buildResponse(String code, String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", code);
        res.put("message", message);
        res.put("timestamp", System.currentTimeMillis());
        return res;
    }
}