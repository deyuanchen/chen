package com.chen.upms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Tiltle: com.chen.upms.admin.controller </p>
 * <p>Description: 登录首页 </p>
 *
 * @Author chen
 * @data: 2017-08-19
 * @version: 1.0
 */
@Controller
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController {
    @ApiOperation(value = "认证中心首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        // 判断请求认证系统是否注册

        return "index";
    }
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "";
    }
}
