package com.chen.upms.client.shiro.filter;

import com.chen.common.util.PropertiesFileUtil;
import com.chen.common.util.RedisUtil;
import com.chen.upms.common.UpmsConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    // 局部会话key
    private final static String CHEN_UPMS_CLIENT_SESSION_ID = "chen-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String CHEN_UPMS_CLIENT_SESSION_IDS = "chen-upms-client-session-ids";

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 判断请求类型
        String upmsType = PropertiesFileUtil.getInstance("chen-upms-client").get("upms.type");
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        if ("client".equals(upmsType)) {
            return validateClient(request, response);
        }
        if ("server".equals(upmsType)) {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
    /**
     * 认证中心登录成功带回code
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = RedisUtil.get(CHEN_UPMS_CLIENT_SESSION_ID + "_" + session.getId());

        return false;
    }
}
