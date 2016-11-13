package com.cqupt.mis.erp.controller.registerlogin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.forwardquarter.ForwardQuarterService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
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
 * Created by 杨青 on 2016/9/3.
 */
@Controller("gameGroupMemberController")
@RequestMapping("/gameGroupMember")
public class GameGroupMemberController {
    @Resource
    private GameGroupMemberService gameGroupMemberService;
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private ForwardQuarterService forwardQuarterService;


    // TODO: 2016/9/3 gameGroupMemberService.addJoinInGroup里面的DWRPush.refresh();会导致测试无法通过
    @RequestMapping("/joinGroup.do")
    public void joinGroup(String gameGroupName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userId = (String) request.getSession().getAttribute("userId");

        if (gameGroupName != null && !"".equals(gameGroupName)){
            boolean isGameGroupMember = gameGroupMemberService.isGroupMember(userId);
            if (!isGameGroupMember){
                boolean result = gameGroupMemberService.addJoinInGroup(gameGroupName, userId);
                if (result){
                    request.getSession().setAttribute("groupName", gameGroupName);
                    map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功加入游戏组", null);

                    JSONUtils.toJSON(map, response);
                    return;
                }
            } else {
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "您已加入该游戏组", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "加入游戏组失败", null);
        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/4 想办法重构发该方法并且添加测试方法，没弄清楚管理员是不是指游戏组创建者
    // 管理员踢出用户
    @RequestMapping("/adminDeleteGameGroupMember.do")
    public void adminDeleteGameGroupMember(String userId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String groupName = (String) request.getSession().getAttribute("groupName");
        if (userId != null && !"".equals(userId)){
            String[] userIds = userId.split(",");
            for (String userID: userIds){
                gameGroupMemberService.deleteExitGroup(groupName, userID);
            }
            gameGroupMemberService.exitGroupReload(groupName);
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "删除成功", null);
        JSONUtils.toJSON(map,response);
    }

    // 退出游戏
    @RequestMapping("/exitGame.do")
    public void exitGame(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;

        // 为了能够有记录，这里不能删除这些人的信息，但是要分破产的处理方式一样
        String groupName = (String) request.getSession().getAttribute("groupName");
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        //完成这里的所有投放广告
        advertisementService.updateAdvertisementFinish(userUnique);

        forwardQuarterService.bankruptcy(userUnique); //该方法里面的DWRPush.refresh();会造成空指针异常
        int status = -1;
        gameGroupMemberService.updateBankruptcyUserStatus(userUnique, status);
        gameGroupMemberService.updateGameGroupMemberNumber(groupName);
        request.getSession().invalidate();

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功退出小组", null);
        JSONUtils.toJSON(map,response);
    }

    // 用户主动退出小组  //FIXME
    @RequestMapping("/exitGroup.do")
    public Map exitGroup(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userId = (String) request.getSession().getAttribute("userId");
        String groupName = (String) request.getSession().getAttribute("groupName");
        System.out.print("这里是3");
        try {
            System.out.print("这里是7");
            if (userId != null && !"".equals(userId)){
                System.out.print("这里是8");
                gameGroupMemberService.deleteExitGroup(groupName, userId);
                request.getSession().removeAttribute("groupName");
                System.out.print("这里是4");
            }
        } catch (Exception e){
            e.printStackTrace();
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "退出失败", userId);
            JSONUtils.toJSON(map, response);
            System.out.print("这里是5");
            return map;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "退出成功", userId);
        JSONUtils.toJSON(map, response);
        System.out.print("这里是6");
        return map;
    }

    //FIXME 还没写测试，因为不清楚这个方法在哪里使用
    @RequestMapping("/replay.do")
    public void replay(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        int status = -1;

        gameGroupMemberService.updateBankruptcyUserStatus(userUnique, status);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, null);
        JSONUtils.toJSON(map, response);
    }

    /**
     * showGameGroupMemberListAJAX 进入房间之后立马加载一次或者有新人新人进来之后立马加载.
     * 与在service的方法不一样, 这个是主动获取的, 可以避免推送出现的故障
     * @param request
     * @param response
     */
    @RequestMapping("/showGameGroupMemberListAJAX.do")
    public void showGameGroupMemberListAJAX(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String groupName = (String) request.getSession().getAttribute("groupName");

        List<ApprovedUserInfo> members = gameGroupMemberService.findGameGroupUserMessageByGroupName(groupName);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, members);
        JSONUtils.toJSON(map, response);
    }
}
