package com.chen.upms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>Tiltle: com.chen.upms.admin.controller </p>
 * <p>Description: 后台管理首页 </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController {
    /**
     * 后台首页
     * @return
     */
    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.addAttribute("host", "后台管理首页");
        return "index";
    }
}
