package com.chen.api.gateway.filter;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * <p>Tiltle: com.chen.api.gateway.filter </p>
 * <p>Description: TODO(这里来描述信息) </p>
 *
 * @Author chen
 * @data: 2017-08-15
 * @version: 1.0
 */
public class DidiErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes (
            RequestAttributes requestAttributes, boolean includeStackTrace){
        Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
        result.remove("exception");
        return result;
    }

}
