package com.cqupt.mis.erp.controller.iso;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.iso.ISODeveloped;
import com.cqupt.mis.erp.model.iso.ISODeveloping;
import com.cqupt.mis.erp.model.iso.ISOUndevelop;
import com.cqupt.mis.erp.service.iso.ISOManageService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/11.
 */
@Controller("isoController")
@RequestMapping("/iso")
public class IsoController {
    @Resource
    private ISOManageService isoManageService;

    // TODO: 2016/9/11 接口返回值比较特殊，和前端交流一下
    @RequestMapping("/showISO.do")
    public void showISO(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<ISODeveloped> isoDevelopedList = isoManageService.findAllISODeveloped(userUnique);
        List<ISODeveloping> isoDevelopingList = isoManageService.findAllISODeveloping(userUnique);
        List<ISOUndevelop> isoUndevelopList = isoManageService.findAllISOUndevelop(userUnique);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("isoDevelopedList", isoDevelopedList);
        map.put("isoDevelopingList", isoDevelopingList);
        map.put("isoUndevelopList", isoUndevelopList);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/addISOToISODeveloping.do")
    public void addISOToISODeveloping(String isoName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        try {
            isoManageService.addISOToISODeveloping(userUnique, isoName);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "开始开发", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法开发", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // FIXME 以下四个方法还没有测试
    // 继续维护ISO
    @RequestMapping("/updateISODevelopedStatusToOne.do")
    public Map updateISODevelopedStatusToOne(String isoName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try{
            isoManageService.updateISODevelopedStatusToOne(userUnique, isoName);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "维护成功", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法恢复维护", null);
        }
        JSONUtils.toJSON(map, response);
        return map;
    }

    // 暂停维护ISO
    @RequestMapping("/updateISODevelopedStatusToZero.do")
    public Map updateISODevelopedStatusToZero(String isoName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try{
            isoManageService.updateISODevelopedStatusToZero(userUnique, isoName);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "暂停维护", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法暂停维护", null);
        }
        JSONUtils.toJSON(map, response);
        return map;
    }

    // 开始开发
    @RequestMapping("/updateISODevelopingStatusToOne.do")
    public Map updateISODevelopingStatusToOne(String isoName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try{
            isoManageService.updateISODevelopingStatusToOne(userUnique, isoName);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "开始开发", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法开始开发", null);
        }
        JSONUtils.toJSON(map, response);
        return map;
    }

    // 暂停开发
    @RequestMapping("/updateISODevelopingStatusToZero.do")
    public Map updateISODevelopingStatusToZero(String isoName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try{
            isoManageService.updateISODevelopingStatusToZero(userUnique, isoName);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "暂停开发", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法暂停开发", null);
        }
        JSONUtils.toJSON(map, response);
        return map;
    }
}
