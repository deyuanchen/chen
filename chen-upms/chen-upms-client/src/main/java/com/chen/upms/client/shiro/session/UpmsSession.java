package com.chen.upms.client.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * <p>Tiltle: com.chen.upms.client.shiro.session </p>
 * <p>Description: 重写session</p>
 *
 * @Author chen
 * @data: 2017-08-20
 * @version: 1.0
 */
public class UpmsSession extends SimpleSession {

    public static enum OnlineStatus {
        on_line("在线"), off_line("离线"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    // 用户浏览器类型
    private String userAgent;

    // 在线状态
    private OnlineStatus status = OnlineStatus.off_line;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

}
