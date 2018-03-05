package com.startcaft.basic.core.exceptions;

/**
 * 字段(参数)缺少异常
 * @author startcaft
 * @date 2018/3/1
 */
public class FieldNullException extends BasicProException {

    public FieldNullException() {
    }

    public FieldNullException(String message) {
        super(message);
    }

    public FieldNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNullException(Throwable cause) {
        super(cause);
    }

    public FieldNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
