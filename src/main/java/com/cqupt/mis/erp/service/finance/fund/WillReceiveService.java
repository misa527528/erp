package com.cqupt.mis.erp.service.finance.fund;

import com.cqupt.mis.erp.model.finance.WillReceive;

import java.util.List;

public interface WillReceiveService {

	/**
	 * 向应收账款中插入一条记录
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @param money 金额
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param willReceiveDescription 对应收账款的文字说明,口水多过茶
	 * @param note 擦,这又是备注
	 */
	void addWillReceive(String userUnique, Double money,
							   Integer beginTime, Integer endTime, String willReceiveDescription,
							   String note);
	
	/**
	 * 删除一条应收账款记录
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param willReceiveID
	 *            应收账款码
	 */
	void delete(String userUnique, Integer willReceiveID);
	
	/**
	 * 查询应收账款
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return
	 */
	List<WillReceive> findWillReceive(String userUnique);
	
	/**
	 * 处理贴现业务
	 * 
	 * @param userUnique 用户唯一码
	 * @param willReceiveID 应收账款码
	 * @return
	 */
	void discount(String userUnique, Integer willReceiveID);
	
}
