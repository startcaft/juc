/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/13 14:50
 * Description: SQL执行结果异常，插入，修改，删除一条数据时，影响条目数不为1
 */
package com.startcaft.basic.core.exceptions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈SQL执行结果异常，插入，修改，删除一条数据时，影响条目数不为1〉
 *
 * @author StartCaft
 * @create 2018/3/13
 * @since 1.0.0
 */
public class SqlExecuteException extends BasicProException {

    public SqlExecuteException() {
    }

    public SqlExecuteException(String message) {
        super(message);
    }

    public SqlExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlExecuteException(Throwable cause) {
        super(cause);
    }

    public SqlExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
