package com.cqupt.mis.erp.controller.admin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.enterpriseevaluate.*;
import com.cqupt.mis.erp.service.enterpriseevaluate.EnterPriseEvaluateService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author jyo
 */
@Controller("enterpriseEvaluateController")
@RequestMapping("/enterpriseEvaluate")
public class EnterpriseEvaluateController {

	@Resource
	private EnterPriseEvaluateService enterpriseEvaluateService;

	// 根据游戏组名获得各小组信息
	@RequestMapping("/showGroupMembers.do")
	public void showGroupMembers(HttpServletResponse response) {
		List<Object> list = enterpriseEvaluateService.showGroupMembers();

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "获取成功", list);
		JSONUtils.toJSON(map, response);
	}

	// 查看所有者权益(按企业查看)
	@RequestMapping("/showEndValue.do")
	public void showEndValue(HttpServletResponse response, String year, String groupName, String userunique) {
		List<Object> endValues = enterpriseEvaluateService.showEndValue(year, groupName, userunique);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "获取成功", endValues);
		JSONUtils.toJSON(map, response);
	}

	// 查看所有者权益(按游戏组查看)
	@RequestMapping("/showEndValues.do")
	public void showEndValues(HttpServletResponse response, String year, String groupName, String period) {
		List<Object> endValues = enterpriseEvaluateService.showEndValues(year, period, groupName);

		Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "获取成功", endValues);
        JSONUtils.toJSON(map, response);
	}

	// 企业评价-->广告投入产出分析
	@RequestMapping("/getUserIORatesOfAd.do")
	public void getUserIORatesOfAd(String groupName, String year, HttpServletResponse response) {
		List<Map<String, Object>> userIORates = enterpriseEvaluateService.getUserIORatesOfAd(groupName, year);

		Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", userIORates);
		JSONUtils.toJSON(map, response);
	}

	// 企业评价-->综合市场占有率分析
	@RequestMapping("/getGeneralMarketShare.do")
	public void getGeneralMarketShare(String groupName, String year, HttpServletResponse response) {
		List<MemberSaleOfMarket> memberSaleOfMarkets = enterpriseEvaluateService.getGeneralMarketShare(groupName, year);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberSaleOfMarkets);
		JSONUtils.toJSON(map, response);
	}

	// 企业评价-->产品市场占有率分析
	@RequestMapping("/getProductMarketShare.do")
	public void getProductMarketShare(String groupName, String year, HttpServletResponse response) {
		List<MemberSaleOfProduct> memberSaleOfProducts =
                enterpriseEvaluateService.getProductMarketShare(groupName, year);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberSaleOfProducts);
		JSONUtils.toJSON(map, response);
	}

	// 企业评价-->成本费用占销售比例分析
	@RequestMapping("/getCostStructure.do")
	public void getCostStructure(String groupName, String year, HttpServletResponse response) {
		List<MemberCost> memberCosts = enterpriseEvaluateService.getCostStructure(groupName, year);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberCosts);
		JSONUtils.toJSON(map, response);
	}

	// 企业评价-->成本费用占销售比例变化分析
	@RequestMapping("/getCostStructureChanges.do")
	public Map getCostStructureChanges(String groupName, String userunique, HttpServletResponse response) {
		List<MemberCost> memberCosts = enterpriseEvaluateService.getCostStructureChanges(groupName, userunique);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", memberCosts);
		JSONUtils.toJSON(map, response);
		return map;
	}

	// 企业评价-->产品贡献利润分析
	@RequestMapping("/getProductsProfit.do")
	public void getProductsProfit(String groupName, String year, HttpServletResponse response) {
		List<ProductProfit> productProfits = enterpriseEvaluateService.getProductsProfit(groupName, year);

        Map<String, Object>	map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", productProfits);
		JSONUtils.toJSON(map, response);
	}

	// 企业评价-->生产能力分析
	@RequestMapping("/getMembersCapacity.do")
	public void getMembersCapacity(String groupName, HttpServletResponse response) {
		List<ProduceCapacity> produceCapacities = enterpriseEvaluateService.getMembersCapacity(groupName);

        Map<String, Object>	map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功", produceCapacities);
		JSONUtils.toJSON(map, response);
	}
}
