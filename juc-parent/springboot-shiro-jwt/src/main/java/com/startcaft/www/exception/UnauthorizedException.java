package com.startcaft.www.exception;

/**
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 */
public class UnauthorizedException extends  RuntimeException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
