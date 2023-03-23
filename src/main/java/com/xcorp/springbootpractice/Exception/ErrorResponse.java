package com.xcorp.springbootpractice.Exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String code;
    private String status;

    public ErrorResponse(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
