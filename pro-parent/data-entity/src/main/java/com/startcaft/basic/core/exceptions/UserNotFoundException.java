/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/13 14:42
 * Description: 没有查询到指定用户时的异常
 */
package com.startcaft.basic.core.exceptions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈没有查询到指定用户时的异常〉
 *
 * @author StartCaft
 * @create 2018/3/13
 * @since 1.0.0
 */
public class UserNotFoundException extends BasicProException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
