package com.superbaboon.web.framework.core;

/**
 * we think each exceptional case of web api should bind to different error code.
 *
 * client can do something according to the error codes, these errors cant be divided into two parts:
 * 1.user errors: different messages should be displayed to users(ex: input error phone, error name)
 * 2.system errors: user cant do anything but to be mentioned system errors kindly
 *
 * besides this, different error code can be convenient to locate the actual problem
 *
 * Created by junjiewu on 16/4/5.
 */
public class ErrorCode {

    private int errorCode;

    private String errorMessage;

    public ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
