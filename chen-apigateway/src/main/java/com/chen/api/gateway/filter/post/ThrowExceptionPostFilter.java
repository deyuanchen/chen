package com.chen.api.gateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

/**
 * <p>Tiltle: com.chen.api.gateway.filter.post </p>
 * <p>Description: TODO(这里来描述信息) </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
@Component
public class ThrowExceptionPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        // doSomething();
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }

}