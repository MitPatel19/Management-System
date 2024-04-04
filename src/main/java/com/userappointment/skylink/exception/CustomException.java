package com.userappointment.skylink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomException extends Exception{

    private int errorCode;


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, int i) {
        super(message);
        setErrorCode(i);

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
