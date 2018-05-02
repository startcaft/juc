/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/2 16:10
 * Description:
 */
package com.startcaft.basic.redisfactory;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/5/2
 * @since 1.0.0
 */
public class DicRedis extends AbstractRedis {

    public DicRedis() {
        this.timeUnit = TimeUnit.HOURS;
        this.expireTime = 24;
        this.keyPrefix = "dic:";
    }

    public DicRedis(String keyPrefix) {
        this.timeUnit = TimeUnit.HOURS;
        this.expireTime = 24;
        this.keyPrefix = keyPrefix;
    }

    public DicRedis(TimeUnit timeUnit,long keyExpireTime,String keyPrefix) {
        this.timeUnit = timeUnit;
        this.expireTime = keyExpireTime;
        this.keyPrefix = keyPrefix;
    }
}
