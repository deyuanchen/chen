package com.chen.api.gateway.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * <p>Tiltle: com.chen.api.gateway.filter.error </p>
 * <p>Description: ErrorExtFilter错误异常拦截 </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
@Component
public class ErrorExtFilter extends SendErrorFilter {

    Logger log = LoggerFactory.getLogger(ErrorExtFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        if(failedFilter != null && failedFilter.filterType().equals("post")) {
            return true;
        }
        return false;
    }

}

