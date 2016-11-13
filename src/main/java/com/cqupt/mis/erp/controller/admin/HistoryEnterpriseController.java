package com.cqupt.mis.erp.controller.admin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.enterpriseevaluate.*;
import com.cqupt.mis.erp.service.enterpriseevaluate.HistoryEnterPriseService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller("historyEnterpriseController")
@RequestMapping("/historyEnterprise")
public class HistoryEnterpriseController {
	@Resource
	private HistoryEnterPriseService historyEnterPriseService;

	// 根据游戏组名获得各小组信息
	@RequestMapping("/showGroupMembers.do")
	public void showGroupMembers(HttpServletResponse response) {
        Map<String, Object> map;

        List<Object> list = historyEnterPriseService.showGroupMembers();
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", list);
		JSONUtils.toJSON(map, response);
	}

	// 查看所有者权益
	@RequestMapping("/shouwEndValue.do")
	public void showEndValue(HttpServletResponse response, String year, String groupName, String userunique) {
        Map<String, Object> map;

        List<Object> endValues = historyEnterPriseService.showEndValue(year, groupName, userunique);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", endValues);
		JSONUtils.toJSON(map, response);
	}

	// 查看所有者权益(新)
	@RequestMapping("/showEndValues.do")
	public void showEndValues(HttpServletResponse response, String year, String groupName, String period) {
        Map<String, Object> map;

        List<Object> endValues = historyEnterPriseService.showEndValues(year, period, groupName);

		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", endValues);
		JSONUtils.toJSON(map, response);
	}

	// 广告投入产出分析
	@RequestMapping("/getUserIORatesOfAd.do")
	public void getUserIORatesOfAd(HttpServletResponse response, String year, String groupName) {
        Map<String, Object> map;

        List<Map<String, Object>> userIORates = historyEnterPriseService.getUserIORatesOfAd(groupName, year);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", userIORates);
		JSONUtils.toJSON(map, response);
	}

	// 综合市场占有率分析
	@RequestMapping("/getGeneralMarketShare.do")
	public void getGeneralMarketShare(HttpServletResponse response, String year,String groupName) {
        Map<String, Object> map;

        List<MemberSaleOfMarket> memberSaleOfMarkets = historyEnterPriseService.getGeneralMarketShare(groupName, year);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberSaleOfMarkets);
		JSONUtils.toJSON(map, response);
	}

	// 产品市场占有率分析
	@RequestMapping("/getProductMarketShare.do")
	public void getProductMarketShare(HttpServletResponse response, String year, String groupName) {
        Map<String, Object> map;

        List<MemberSaleOfProduct> memberSaleOfProducts = historyEnterPriseService.getProductMarketShare(groupName, year);

		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberSaleOfProducts);
		JSONUtils.toJSON(map, response);
	}

	// 成本费用占销售比例分析
	@RequestMapping("/getCostStructure.do")
	public void getCostStructure(HttpServletResponse response, String year, String groupName) {
        Map<String, Object> map;

        List<MemberCost> memberCosts = historyEnterPriseService.getCostStructure(groupName, year);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberCosts);
		JSONUtils.toJSON(map, response);
	}

	// 成本费用占销售比例变化分析
	@RequestMapping("/getCostStructureChanges.do")
	public void getCostStructureChanges(HttpServletResponse response, String userunique, String groupName) {
        Map<String, Object> map;

        List<MemberCost> memberCosts = historyEnterPriseService.getCostStructureChanges(groupName, userunique);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberCosts);
		JSONUtils.toJSON(map, response);
	}

	// 产品贡献利润分析
	@RequestMapping("/getProductsProfit.do")
	public void getProductsProfit(HttpServletResponse response, String year, String groupName) {
        Map<String, Object> map;

        List<ProductProfit> productProfits = historyEnterPriseService.getProductsProfit(groupName, year);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", productProfits);
		JSONUtils.toJSON(map, response);
	}

	// 生产能力分析
	@RequestMapping("/getMembersCapacity.do")
	public void getMembersCapacity(HttpServletResponse response, String groupName) {
        Map<String, Object> map;

        List<ProduceCapacity> produceCapacities = historyEnterPriseService.getMembersCapacity(groupName);
		map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", produceCapacities);
		JSONUtils.toJSON(map, response);
	}
}
