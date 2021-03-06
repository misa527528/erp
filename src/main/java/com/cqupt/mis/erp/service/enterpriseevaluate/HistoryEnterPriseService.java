package com.cqupt.mis.erp.service.enterpriseevaluate;

import com.cqupt.mis.erp.model.enterpriseevaluate.*;

import java.util.List;
import java.util.Map;



/**
 * HistoryEnterPriseService
 * 这个类是一个冗余的类,如果需要修改EnterPriceEval.Service类的时候必须同时修改这个类. 
 * 因为这个类 是一个复制过来的. 所有方法名称基本没有过多的变化.
 * 2015年6月14日 上午11:23:07
 * @author 何海源
 */
public interface HistoryEnterPriseService {

	
	List<AdminIncomeBean> getUserUnique(String groupname, int currentperiod);

	/**
	 * 计算个人所有者权益合计
	 * @param userUnique
	 * @param period
	 * @return
	 */
	double CalOwnerBenifit(String userUnique, int period);

	/**
	 * showEndValue 按企业查看所有者权益 
	 * @param year
	 * @param groupName
	 * @param userunique
	 * @return 
	 *List<Object>
	 */
	List<Object> showEndValue(String year, String groupName, String userunique);
	
	
	/**
	 * showEndValues 按游戏组查看所有者权益 
	 * @param year
	 * @param period
	 * @param groupName
	 * @return 
	 *List<Object>
	 */
	List<Object> showEndValues(String year, String period, String groupName);

	/**
	 * 广告投入产出分析
	 * @param groupName
	 * @param year
	 */
	List<Map<String, Object>> getUserIORatesOfAd(String groupName, String year);

	/**
	 *  综合市场占有率分析
	 * @param groupName
	 * @param year
	 */
	List<MemberSaleOfMarket> getGeneralMarketShare(String groupName, String year);

	/**
	 * 产品市场占有率分析
	 * @param groupName
	 * @param year
	 */
	List<MemberSaleOfProduct> getProductMarketShare(String groupName, String year);

	/**
	 * 成本费用占销售比例分析
	 * @param groupName
	 * @param year
	 */
	List<MemberCost> getCostStructure(String groupName, String year);

	/**
	 * 成本费用占销售比例变化分析
	 * @param groupName
	 * @param userunique
     * @return
     */
	List<MemberCost> getCostStructureChanges(String groupName, String userunique);

	/**
	 * 产品贡献利润分析
	 * @param groupName
	 * @param year
	 */
	List<ProductProfit> getProductsProfit(String groupName, String year);

	/**
	 * 生产能力分析
	 * @param groupName
	 * @return
     */
	List<ProduceCapacity> getMembersCapacity(String groupName);

	
	List<Object> showGroupMembers();

}
