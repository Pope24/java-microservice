package com.commonService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    private String code;
    private String message;
    private HttpStatus httpStatus;

    public ErrorMessage() {
    }

    public ErrorMessage(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
