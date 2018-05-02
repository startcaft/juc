/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/2 16:07
 * Description: redis操作基础类
 */
package com.startcaft.basic.redisfactory;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈redis操作基础类〉
 *
 * @author StartCaft
 * @create 2018/5/2
 * @since 1.0.0
 */
public abstract class AbstractRedis {

    protected TimeUnit timeUnit;

    protected long expireTime;

    protected String keyPrefix;

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
