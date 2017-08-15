package com.chen.upms.admin.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <p>Tiltle: com.chen.upms.admin.controller </p>
 * <p>Description: 后台管理控制器 </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
@Controller
public class ManageController {
    /**
     * 后台首页
     * @return
     */
    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/index.jsp";
    }
}
