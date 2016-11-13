package com.cqupt.mis.erp.utils.interceptor;

import com.cqupt.mis.erp.utils.JSONUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/29.
 */
public class ResponseInterceptor extends HandlerInterceptorAdapter {
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView){
        Map<String, Object> map = new HashMap<>();

        map.put("success", "success");
        JSONUtils.toJSON(map, response);
    }
}
