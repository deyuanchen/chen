package com.chen.api.gateway.filter;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <p>Tiltle: com.chen.api.gateway.filter </p>
 * <p>Description: TODO(这里来描述信息) </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
public class DidiFilterProcessor extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.exception", e);
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
