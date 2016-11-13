package com.cqupt.mis.erp.utils.interceptor;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.utils.JSONUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/29.
 */
public class IsLoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(request.getSession().getAttribute("userId") != null || request.getSession().getAttribute("adminId") != null){
            return true;
        }
        else {
            Map<String , Object> map = new HashMap<>();
            map.put("status", ReturnStatus.ERROR);
            map.put("msg", "您还未登录！");
            map.put("data", null);
            JSONUtils.toJSON(map, response);
            return false;
        }
    }
}
