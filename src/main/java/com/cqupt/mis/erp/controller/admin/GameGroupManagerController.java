package com.cqupt.mis.erp.controller.admin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberStatus;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.forwardquarter.ForwardQuarterService;
import com.cqupt.mis.erp.service.order.ChooseOrderService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by jyo on 2016/9/18.
 */
@Controller("gameGroupManagerController")
@RequestMapping("/gameGroupManager")
public class GameGroupManagerController {
	@Resource
	private GameGroupService gameGroupService;
	@Resource
	private ForwardQuarterService forwardQuarterService;
	@Resource
	private AdvertisementService advertisementService;
	@Resource
	private ChooseOrderService chooseOrderService;

	// 展示游戏组列表
	@RequestMapping("/showGameGroups.do")
	public void showGameGroups(HttpServletResponse response) {
		List<GameGroupInfo> GameGroupInfos = gameGroupService.showGameGroups();

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", GameGroupInfos);
		JSONUtils.toJSON(map, response);
	}

	// 删除游戏组
	@RequestMapping("/deteleGameGroup.do")
	public void deteleGameGroup(HttpServletResponse response, String groupName) {
        Map<String, Object> map;

        boolean result = gameGroupService.delete_GameGroupByGroupName(groupName);

		if (result) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
		} else {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
		}
		JSONUtils.toJSON(map, response);
	}

	// 连表查询用户基本信息和游戏状态信息
	@RequestMapping("/findGameGroupMemberStatusByGroupName.do")
	public void findGameGroupMemberStatusByGroupName(HttpServletResponse response, String groupName) {
		List<GameGroupMemberStatus> gameGroupMemberStatuss =
                gameGroupService.findGameGroupMemberStatusByGroupName(groupName);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", gameGroupMemberStatuss);
		JSONUtils.toJSON(map, response);
	}

	// 结束经营
	@RequestMapping("/endPlayGame.do")
	public void endPlayGame(HttpServletResponse response, String userUnique, String groupName) {
        Map<String, Object> map;
        int result = gameGroupService.endPlayGame(userUnique, groupName);

		if (result == 1) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
		} else {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
		}
		JSONUtils.toJSON(map, response);
	}

    // TODO: 2016/9/21 这里有三种状态，和前端沟通一下  
    // 结束投广告
	@RequestMapping("/endAdvertising.do")
	public Map endAdvertising(HttpServletResponse response, String userUnique) {
        Map<String, Object> map;
        int code = advertisementService.updateAdvertisementFinish(userUnique);

		if (code == 1) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "投放的钱太多.不能投了", null);
		} else if (code == 2) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "投放成功", null);
		} else {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "已经进行过结束广告费投放操作", null);
		}
		JSONUtils.toJSON(map, response);
		return map;
	}

	// 结束选订单
	@RequestMapping("/endChooseOrder.do")
	public Map endChooseOrder(HttpServletResponse response, String userUnique) {
        Map<String, Object> map;

        int code = chooseOrderService.updateEndChooseOrder(userUnique);
		if (code == 1) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "结束选订单成功", null);
		} else if (code == 2) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "已经完成这一期的选择订单的操作", null);
		} else {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "没有完成投放广告", null);
		}
		JSONUtils.toJSON(map, response);
		return map;
	}

	// 推进下一周期
	@RequestMapping("/ForwarPeriod.do")
	public Map ForwarPeriod(HttpServletResponse response, String userUnique) {
        Map<String, Object> map;
        Integer status = forwardQuarterService.ForwardStatus(userUnique);

		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", status);
		JSONUtils.toJSON(map, response);
		return map;
		/**
		 * boolean result =
		 * gameGroupMemberDao.updateBankruptcyUserStatus(userUnique); String
		 * results; if(result){ results = "推进下一周期成功"; }else{ results =
		 * "推进下一周期失败"; } Map<String, Object> map = new HashMap<String,
		 * Object>(); map.put("code", 0); map.put("result", results);
		 * JSONUtils.toJSON(map);
		 */
	}

    /**
     * 将小组的记录转成历史记录,同时删除记录内容
     * @param response
     * @param groupName
     */
	@RequestMapping("/changeToHistory.do")
	public Map changeToHistory(HttpServletResponse response, String groupName) {
        Map<String, Object> map;

        boolean result = gameGroupService.addChangeToHistory(groupName);
		if (result) {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", null);
		} else {
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "失败", null);
		}
		JSONUtils.toJSON(map, response);
		return map;
	}

}
