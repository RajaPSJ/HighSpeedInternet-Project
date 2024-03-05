package org.example.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class CustomerApiException extends RuntimeException {
    private final HttpStatus error;

    public CustomerApiException(HttpStatus error, String message) {
        this(error, message, null);
    }

    public CustomerApiException(HttpStatus error, String message, Throwable t) {
        super(message, t);
        this.error = error;
    }
}
