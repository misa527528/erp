package com.cqupt.mis.erp.controller.registerlogin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.service.inigame.IniGameService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/2.
 */
@Controller("GameGroupController")
@RequestMapping("/gameGroup")
public class GameGroupController {
    @Resource
    private GameGroupService gameGroupService;
    @Resource
    private GameGroupMemberService gameGroupMemberService;
    @Resource
    private IniGameService iniGameService;


    // FIXME 测试时候报空指针异常
    @RequestMapping("/showGameGroupList.do")
    public Map showGameGroupList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        HttpSession session = request.getSession();

        List<GameGroupInfo> gameGroups = gameGroupService.showGameGroupList(session);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, gameGroups);
        JSONUtils.toJSON(map, response);

        return map;
    }

    @RequestMapping("/isGameGroupExist.do")
    public void isGameGroupExist(String groupName, HttpServletResponse response){
        Map<String, Object> map;
        GameGroupInfo gameGroup = gameGroupService.findGameGroupInfoByGroupName(groupName);

        if (gameGroup != null){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "该分组已经存在", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "该分组已经存在", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // 创建分组
    @RequestMapping("/addGameGroup.do")
    public void addGameGroup(GameGroupInfo gameGroupInfo, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String gameGroupCreatorId = (String) request.getSession().getAttribute("userId");
        gameGroupInfo.setGroupCreaterId(gameGroupCreatorId);

        boolean result = gameGroupService.addGameGroup(gameGroupInfo);

        if (result){
            request.getSession().setAttribute("groupName", gameGroupInfo.getGroupName());
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "创建分组成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "游戏组已经存在", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/isGameGroupRunning.do")
    public void isGameGroupRunning(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String groupName = (String) request.getSession().getAttribute("groupName");

        if (groupName != null && !"".equals(groupName)){
            GameGroupInfo gameGroup = gameGroupService.findGameGroupInfoByGroupName(groupName);

            if (gameGroup != null && gameGroup.getCurrentPeriod() > 0){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "游戏已经开始", null);

                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "游戏未开始", null);
        JSONUtils.toJSON(map, response);
    }

    /**
     * 获取分组信息 然后直接展示, 满人的不展示,已经开始的也不会展示
     * @param request
     * @param response
     */
    // TODO: 2016/9/3 这里需要想想“满人的不展示,已经开始的也不会展示”怎么实现？改接口在哪里调用了？
    @RequestMapping("/getGameGroupMessage.do")
    public void getGameGroupMessage(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String gameGroupName = (String) request.getSession().getAttribute("groupName");

        if (gameGroupName == null && "".equals(gameGroupName)){
            String userId = (String) request.getSession().getAttribute("userId");
            gameGroupName = gameGroupMemberService.findGroupNameByUserId(userId);
            request.getSession().setAttribute("groupName", gameGroupName);
        }

        GameGroupInfo gameGroup = gameGroupService.findGameGroupInfoByGroupName(gameGroupName);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, gameGroup);

        JSONUtils.toJSON(map, response);
    }

    // 管理员删除分组
    @RequestMapping("/deleteGameGroup.do")
    public void deleteGameGroup(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String gameGroupName = (String) request.getSession().getAttribute("groupName");

        if (gameGroupName != null && ! "".equals(gameGroupName)){
            boolean result = gameGroupService.deleteGameGroupByGroupName(gameGroupName);
            if (result){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "删除成功", null);
                System.out.println("执行到这里1");
                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "删除失败", null);
        JSONUtils.toJSON(map, response);
    }

    // FIXME 这里还没有解决
    @RequestMapping("/startGame.do")
    public synchronized void startGame(HttpServletRequest request, HttpServletResponse response){
        String groupName = (String) request.getSession().getAttribute("groupName");

        gameGroupMemberService.startGameForward(groupName);
        try {
            GameGroupInfo gameGroup = gameGroupService.findGameGroupInfoByGroupName(groupName);
            if (gameGroup.getCurrentPeriod() > 0){
                return;
            }
            boolean result = iniGameService.iniGame(groupName);
            // FIXME 这个秒数可能要影响加载的问题. 可能后面要根据问题来解决
            //Thread.currentThread().sleep(2000);
            if (result){
                gameGroupMemberService.startGameForwardToMain(groupName);
            } else {
                System.out.println("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }



}
