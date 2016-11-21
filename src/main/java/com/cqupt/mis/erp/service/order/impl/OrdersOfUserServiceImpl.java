package com.cqupt.mis.erp.service.order.impl;

import com.cqupt.mis.erp.manager.order.OrdersOfUserDao;
import com.cqupt.mis.erp.model.order.OrdersOfUser;
import com.cqupt.mis.erp.service.order.OrdersOfUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("ordersOfUserService")
public class OrdersOfUserServiceImpl implements OrdersOfUserService {

	@Resource
	private OrdersOfUserDao ordersOfUserDao;
	
	
	@Override
	public List<OrdersOfUser> findOrdersOfUserByUserUnique(String userUnique) {
		return ordersOfUserDao.findOrdersOfUserByUserUnique(userUnique);	
	}

	@Override
	public List<OrdersOfUser> findDeliveredOrdersOfUser(String userUnique) {
		return ordersOfUserDao.findDeliveredOrdersOfUserByUserUnique(userUnique);
	}

	@Override
	public List<OrdersOfUser> findNotDeliverOrdersOfUser(String userUnique) {
		return ordersOfUserDao.findNotDeliverOrdersOfUser(userUnique);
	}

	@Override
	public int findSmallestTime(String userUnique) {
		return ordersOfUserDao.findSmallestTime(userUnique);
	}

	@Override
	public OrdersOfUser findOrderDetail(String orderID) {
		return ordersOfUserDao.findOrderDetail(orderID);
	}

	@Override
	public boolean updateOrderEndTime(OrdersOfUser ordersOfUser) {
		String userUnique = ordersOfUser.getUserUnique();
		String orderID = ordersOfUser.getOrderID();

		int result = ordersOfUserDao.updateOrderEndTime(userUnique, orderID);

		return  result > 0;
	}

	@Override
	public float findSaleRoom(String ordersID) {
		return ordersOfUserDao.findSaleRoom(ordersID);	
	}
		
	
	
	
}
