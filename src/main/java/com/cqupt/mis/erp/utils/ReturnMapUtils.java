package com.cqupt.mis.erp.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/31.
 */
public class ReturnMapUtils {
    /**
     * 用于封装返回的map,减少代码量
     * @param status 状态码
     * @param message 返回信息
     * @param data 返回数据
     * @return
     */
    public static Map putIntoReturnMap(int status, String message, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        map.put("data", data);

        return map;
    }
}
