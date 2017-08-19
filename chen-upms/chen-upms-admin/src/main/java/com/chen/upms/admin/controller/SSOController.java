package com.chen.upms.admin.controller;

import io.swagger.annotations.ApiOperation;
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
public class SSOController {
    @ApiOperation(value = "认证中心首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        return "";
    }
}
