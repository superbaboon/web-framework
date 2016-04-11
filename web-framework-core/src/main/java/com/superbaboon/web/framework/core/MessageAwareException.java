package com.superbaboon.web.framework.core;

/**
 * when web api throw MessageAwareException, client can be displayed error messages
 * normally user errors(ex: invalid params etc)
 *
 * Created by junjiewu on 16/4/5.
 */
public class MessageAwareException extends RuntimeException {

    private ErrorCode errorCode;

    public MessageAwareException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
