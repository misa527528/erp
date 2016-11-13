package com.cqupt.mis.erp.controller.iniGame;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.inigame.IniGameService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/5.
 */
@Controller("iniGameController")
@RequestMapping("/iniGame")
public class IniGameController {
    @Resource
    private IniGameService iniGameService;
    @Resource
    private GameGroupMemberService gameGroupMemberService;

    // TODO: 2016/9/5 添加测试
    @RequestMapping("/iniGame.do")
    public void iniGame(String groupName, String userId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        iniGameService.iniGame(groupName);

        String userUnique = gameGroupMemberService.findUserUniqueByUserId(userId);
        request.getSession().setAttribute("userUnique", userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "初始化成功", null);
        JSONUtils.toJSON(map, response);
    }

}
