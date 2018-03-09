/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/9 10:52
 * Description: 树状节点异常
 */
package com.startcaft.basic.core.exceptions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈树状节点异常〉
 *
 * @author StartCaft
 * @create 2018/3/9
 * @since 1.0.0
 */
public class ParentNodeException extends RuntimeException {
    public ParentNodeException() {
    }

    public ParentNodeException(String message) {
        super(message);
    }

    public ParentNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParentNodeException(Throwable cause) {
        super(cause);
    }

    public ParentNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
