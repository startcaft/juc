/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/3 10:51
 * Description: 字段唯一异常
 */
package com.startcaft.basic.core.exceptions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈字段唯一异常〉
 *
 * @author StartCaft
 * @create 2018/4/3
 * @since 1.0.0
 */
public class UniqueException extends BasicProException {
    public UniqueException() {
    }

    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueException(Throwable cause) {
        super(cause);
    }

    public UniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
