package com.cqupt.mis.erp.service.finance.fund.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cqupt.mis.erp.model.order.OrdersOfUser;
import com.cqupt.mis.erp.service.finance.fund.PenaltyCashService;
import com.cqupt.mis.erp.service.order.OrdersOfUserService;
import com.cqupt.mis.erp.utils.ERPUtils;

@Component("penaltyCashService")
public class PenaltyCashServiceImpl implements PenaltyCashService {

	@Resource
	private OrdersOfUserService ordersOfUserService;
	
	@Override
	public double computePenaltyCash(String orderID) {
		OrdersOfUser ordersOfUser = ordersOfUserService.findOrderDetail(orderID);
		//lx:四舍五入处理
		double penaltyCash = ERPUtils.round(ordersOfUser.getPenalPercent() * ordersOfUser.getpNumber() * ordersOfUser.getPrice() * (ordersOfUser.getEndTime() - ordersOfUser.getNeedTime()));
		return penaltyCash;
	}

}
