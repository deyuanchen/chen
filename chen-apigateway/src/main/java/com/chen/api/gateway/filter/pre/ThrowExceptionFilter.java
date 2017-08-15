package com.chen.api.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Tiltle: com.chen.api.gateway.filter.pre </p>
 * <p>Description: pre首先处理进来的请求 </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {

    /**
     * 拦截的类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 拦截的循序  0表示最大
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行run方法
     * true表示执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            // doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
//            ctx.set("error.message", "有一些错误发生");
        }
        return null;
    }


}
