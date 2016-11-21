package com.cqupt.mis.erp.service.order;

import com.cqupt.mis.erp.model.order.OrdersOfUser;

import java.util.List;

public interface OrdersOfUserService {
	
	/**
	 * 取出用户的所有订单
	 * @author lx
	 * @param userUnique
	 * @return
	 */
	List<OrdersOfUser> findOrdersOfUserByUserUnique(String userUnique);
	
	/**
	 * 取出用户已交货的订单
	 * @author lx
	 * @param userUnique
	 * @return
	 */
	List<OrdersOfUser> findDeliveredOrdersOfUser(String userUnique);
	
	/**
	 * 取出用户还没交货的订单
	 * @param userUnique
	 * @return
	 */
	List<OrdersOfUser> findNotDeliverOrdersOfUser(String userUnique);
	
	/**
	 * 取出最短交货订单的时间
	 * @author lx
	 * @return
	 */
	int findSmallestTime(String userUnique);
	
	/**
	 * 取出用户某个详细的订单
	 * @author lx
	 */
	OrdersOfUser findOrderDetail(String orderID);
	
	/**
	 * 交货时的修改订单状态
	 * @author lx
	 */
	boolean updateOrderEndTime(OrdersOfUser ordersOfUser);
	
	/**
	 * 取出订单销售金额 
	 * @author lx
	 */
	float findSaleRoom(String ordersID);
	
}
