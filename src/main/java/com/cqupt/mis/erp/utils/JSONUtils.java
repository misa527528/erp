package com.cqupt.mis.erp.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

/**
 * 解析json格式的类
 *
 * @author Administrator
 */
public class JSONUtils {
    /**
     * 从json格式转换成一个相应的类
     *
     * @param jsonStr
     * @param cla
     * @return
     */
    public static Object json2Obj(String jsonStr, Class<?> cla) {
        Object obj = null;
        try {
            JSONParser parser = new JSONParser(new StringReader(jsonStr));
            JSONValue jsonValue = parser.nextValue();
            if (jsonValue instanceof com.sdicons.json.model.JSONArray) {
                List<Object> list = new ArrayList<Object>();
                JSONArray jsonArray = (JSONArray) jsonValue;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONValue jsonObj = jsonArray.get(i);
                    Object javaObj = JSONMapper.toJava(jsonObj, cla);
                    list.add(javaObj);
                }
                obj = list;
            } else if (jsonValue instanceof com.sdicons.json.model.JSONObject) {
                obj = JSONMapper.toJava(jsonValue, cla);
            } else {
                obj = jsonValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
        return obj;
    }

    /**
     * 这个方法只能在action方法里面调用一次!
     *
     * @param o
     */
    public static void toJSON(Object o) {
        try {
            String str = JSONMapper.toJSON(o).render(false);
            //TODO 无法设置编码（Myeclipse中可以找到该方法）
            //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
            ServletActionContext.getResponse().getWriter().write(str);
        } catch (MapperException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ServletActionContext.getResponse().getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String toJSONString(Object o) {
        try {
            return JSONMapper.toJSON(o).render(true);
        } catch (MapperException e) {
            e.printStackTrace();
        }
        return null;
    }

}
