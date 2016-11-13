package com.cqupt.mis.erp.controller.admin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.admin.AdminOperateService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/28.
 */
@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminOperateService adminOperateService;

    @RequestMapping("/updateAdminPassword.do")
    public void updateAdminPassword(String adminId, String oldPassword, String newPassword,
                                    HttpServletResponse response){
        Map<String, Object> map;

        boolean result = adminOperateService.updatePWD(adminId, oldPassword, newPassword);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "修改成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "修改失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

}
