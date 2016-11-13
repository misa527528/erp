package com.cqupt.mis.erp.controller.admin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.registerlogin.ApprovedUserService;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
import com.cqupt.mis.erp.service.registerlogin.RegisterInfoService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/16.
 */
@Controller("userManagerController")
@RequestMapping("/userManager")
public class UserManagerController {
    @Resource
    private ApprovedUserService approvedUserService;
    @Resource
    private RegisterInfoService registerInfoService;
    @Resource
    private LoginService loginService;

    // 取出所有审批用户
    @RequestMapping("/findAllApprovedUser.do")
    public void findAllApprovedUser(HttpServletResponse response){
        List<ApprovedUserInfo> approvedUserInfos = approvedUserService.showApprovedUserList();

        Map map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "取出所有审批用户", approvedUserInfos);
        JSONUtils.toJSON(map, response);
    }

    // 取出待审批用户
    @RequestMapping("/findAllRegister.do")
    public void findAllRegister(HttpServletResponse response){
        List<RegisterInfo> approvedUserInfos = registerInfoService.findAllRegister();

        Map map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "取出所有待审批用户", approvedUserInfos);
        JSONUtils.toJSON(map, response);
    }

    // 管理员修改审批用户信息
    @RequestMapping("/updateApprovedUserInfo.do")
    public void updateApprovedUserInfo(String userID, String name, String className, String major, String studentId,
                                       HttpServletResponse response){
        Map<String, Object> map;
        ApprovedUserInfo approvedUserInfo;
        boolean result;
        try {
            approvedUserInfo = loginService.findApprovedUserByUserId(userID);
            approvedUserInfo.setUserID(userID);
            approvedUserInfo.setName(name);
            approvedUserInfo.setClassName(className);
            approvedUserInfo.setMajor(major);
            approvedUserInfo.setStudentID(studentId);

            result = approvedUserService.updateApprovedUserInfo(approvedUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法找到该用户", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "修改成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "修改失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // 审批单个用户
    @RequestMapping("/passRegisterUser.do")
    public void passRegisterUser(String userId, HttpServletResponse response){
        Map<String, Object> map;
        boolean result = approvedUserService.updatePassUser(userId);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "审核通过", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "审核失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // 批量审批,接收一个要审批用户的id数组，遍历
    @RequestMapping("/passBatchRegisterUsers.do")
    public void passBatchRegisterUsers(String[] userIds, HttpServletResponse response){
        for (int i = 0; i < userIds.length; i++) {
            approvedUserService.updatePassUser(userIds[i]);
        }

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "审批通过", null);
        JSONUtils.toJSON(map, response);
    }

    // 删除审批用户
    @RequestMapping("/deleteApprUser.do")
    public void deleteApprUser(String userId, HttpServletResponse response){
        Map<String, Object> map;
        boolean result = approvedUserService.deleteApprovedUserById(userId);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功删除用户", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "删除用户失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // 批量删除,接收一个要删除审批用户的id数组，遍历
    @RequestMapping("/deleteBatchApprUsers.do")
    public void deleteBatchApprUsers(String[] userIds, HttpServletResponse response){
        for (int i=0; i<userIds.length; i++){
            approvedUserService.deleteApprovedUserById(userIds[i]);
        }

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "删除成功", null);
        JSONUtils.toJSON(map, response);
    }

    // 批量删除,接收一个要删除待审批用户的id数组，遍历
    @RequestMapping("/deleteBatchRegiUsers.do")
    public void deleteBatchRegiUsers(String[] userIds, HttpServletResponse response){
        for (int i=0; i<userIds.length; i++){
            approvedUserService.deleteApprovedUserById(userIds[i]);
        }

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "删除成功", null);
        JSONUtils.toJSON(map, response);
    }

    // 删除待审批用户
    @RequestMapping("/deleteRegiUser.do")
    public void deleteRegiUser(String userId, HttpServletResponse response){
        Map<String, Object> map;

        boolean result = registerInfoService.deleteRegisterById(userId);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功删除用户", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "删除用户失败", null);
        }

        JSONUtils.toJSON(map, response);
    }
}
