package com.cqupt.mis.erp.service.order;

import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;

import java.util.ArrayList;
import java.util.List;

public interface AllOrdersOfGroupService {
	
	/**
	 * 添加预测好的所有订单
	 * @author LX
	 * @param allOrdersOfGroups
	 */
	void addAllOrdersOfGroups(ArrayList<AllOrdersOfGroup> allOrdersOfGroups);
		
	/**
	 * 根据游戏组名取出所有预测信息
	 * @author LX
	 * @param groupName
	 * @return
	 */
	List<AllOrdersOfGroup> findAllOrdersOfGroup(String groupName);
	
	/**
	 * 更新某些订单的ISO需求,如果有iso认证会提升iso的价格. 
	 * @author LX
	 * @param allOrdersOfGroups
	 * @return
	 */
	boolean updateSpecalRems(List<AllOrdersOfGroup> allOrdersOfGroups);
	
	/**
	 * findOrderOfGroupByUserUnique 查询所有该期的订单.并且是能选的订单 既 status 为0
	 * @param userUnique
	 * @return 
	 *List<AllOrdersOfGroup>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<AllOrdersOfGroup> findOrderOfGroupByUserUnique(String userUnique);
}
