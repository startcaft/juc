/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/31 15:04
 * Description: Zuul过滤器，参数校验
 */
package com.springcloud.eureka.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Zuul过滤器，参数校验〉
 *
 * @author StartCaft
 * @create 2018/5/31
 * @since 1.0.0
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext resContext = RequestContext.getCurrentContext();
        HttpServletRequest request = resContext.getRequest();

        Object token = request.getParameter("token");
        //校验token
        if(StringUtils.isEmpty(token)){
            resContext.setSendZuulResponse(false);
            resContext.setResponseStatusCode(401);
            resContext.setResponseBody("has no accessToken");
        }
        return null;
    }
}
