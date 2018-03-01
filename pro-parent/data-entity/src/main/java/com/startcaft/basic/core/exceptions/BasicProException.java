package com.startcaft.basic.core.exceptions;

/**
 *
 * @author startcaft
 * @date 2018/3/1
 * 自定义异常
 */
public class BasicProException extends RuntimeException {

    public BasicProException() {
    }

    public BasicProException(String message) {
        super(message);
    }

    public BasicProException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicProException(Throwable cause) {
        super(cause);
    }

    public BasicProException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
