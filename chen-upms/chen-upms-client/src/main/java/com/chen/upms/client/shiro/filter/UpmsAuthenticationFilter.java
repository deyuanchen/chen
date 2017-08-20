package com.chen.upms.client.shiro.filter;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>Tiltle: com.chen.upms.client.shiro.filter </p>
 * <p>Description: 重写authc过滤器 </p>
 *
 * @Author chen
 * @data: 2017-08-19
 * @version: 1.0
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
