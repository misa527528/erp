package com.cqupt.mis.erp.utils.interceptor;

import com.cqupt.mis.erp.utils.JSONUtils;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/29.
 */
@Aspect
public class ResponseAspect {
  //  @AfterReturning(returning = "obj", pointcut = "execution(* com.cqupt.mis.erp.controller.*.*.*(..))")
    public void log(Map obj/*, HttpServletResponse response*/){
        System.out.println("切面起作用了~~~~~~~~~~~~~~~~~~");
        obj.put("success","success");
        System.out.println(obj.get("success"));

        JSONUtils.toJSON(obj, (HttpServletResponse) obj.get("response"));
    }
}
