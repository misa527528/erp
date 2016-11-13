package com.cqupt.mis.erp.controller.registerlogin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.admin.AdminOperateService;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
import com.cqupt.mis.erp.service.registerlogin.RegisterInfoService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/28.
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {
    @Resource
    private RegisterInfoService registerInfoService;
    @Resource
    private AdminOperateService adminOperateService;
    @Resource
    private LoginService loginService;

    @RequestMapping("/cleanSession.do")
    public void cleanSession(HttpServletRequest request){
        if (request.getSession().getAttribute("userId") != null){
            request.getSession().removeAttribute("userId");
        }

        request.getSession().invalidate();
    }

    @RequestMapping("/login.do")
    public void login(String userId, String password, String checkCode, String identity,
                      HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String securityCode = (String) request.getSession().getAttribute("SESSION_SECURITY_CODE");

        /*if (!securityCode.equals(checkCode)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "验证码错误", null);
            JSONUtils.toJSON(map, response);
            return;
        }*/

        if ("teacher".equals(identity)){
            map = adminOperateService.adminUserLogin(userId, password, request);
            JSONUtils.toJSON(map, response);
            return;
        }

        if ("student".equals(identity)){
            map = loginService.loginForword(userId, password, request);
            JSONUtils.toJSON(map, response);
        }
    }

    @RequestMapping("/signout.do")
    public void signout(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String sessionValue = (String) request.getSession().getAttribute("userId");

        if (sessionValue != null){
            request.getSession().invalidate();
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "注销成功", null);
        }
        else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "你还未登录", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getRegister.do")
    public void getRegister(String userId, HttpServletResponse response){
        Map<String, Object> map;
        RegisterInfo register = registerInfoService.findByUserId(userId);

        if (register != null){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, register);
        }
        else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "该用户不存在", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getApprovedUser.do")
    public void getApprovedUser(String userId, HttpServletResponse response){
        Map<String, Object> map;
        ApprovedUserInfo approvedUser = loginService.findApprovedUserByUserId(userId);

        if (approvedUser != null){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, approvedUser);
        }
        else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "该用户不存在", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/modifyUser.do")
    public void modidyUser(RegisterInfo registerInfo, HttpServletResponse response){
        Map<String, Object> map;
        String userId = registerInfo.getUserID();

        if (loginService.findRegisterByUserId(userId) != null &&
                registerInfoService.updatemodifyRegister(registerInfo)){

                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "修改成功", null);
        } else if (loginService.findApprovedUserByUserId(userId) != null &&
                registerInfoService.updateModifyApprovedUser(registerInfo)){

                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "修改成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "修改失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/isUserExist.do")
    public void isUserExist(String userId, HttpServletResponse response){
        Map<String, Object> map;
        boolean result = registerInfoService.isUserExist(userId);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "该用户ID已经存在", null);
        }
        else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, null, null);
        }

        JSONUtils.toJSON(map, response);
        //return map;
    }

    @RequestMapping("/getAllRegisters.do")
    public void getAllRegisters(HttpServletResponse response){
        Map<String, Object> map;
        List<RegisterInfo> registerInfos = registerInfoService.findAllRegister();

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, registerInfos);
        JSONUtils.toJSON(map, response);
    }

    // 注册
    @RequestMapping("/addRegister.do")
    public void addRegister(RegisterInfo registerInfo, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        registerInfo.setStatus("等待审批");

        boolean result = registerInfoService.addRegister(registerInfo);

        if (result){
            request.getSession().setAttribute("userId", registerInfo.getUserID());

            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "注册成功", null);
        }
        else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "注册失败", null);
        }

        JSONUtils.toJSON(map, response);
    }
}