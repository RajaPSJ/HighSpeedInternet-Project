package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Error implements Serializable {
    private String message;
    private int code;

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
