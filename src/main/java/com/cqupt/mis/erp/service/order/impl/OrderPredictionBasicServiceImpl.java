package com.cqupt.mis.erp.service.order.impl;

import com.cqupt.mis.erp.manager.prediction.OrderPredictionBasicDao;
import com.cqupt.mis.erp.model.order.OrderPredictionBasic;
import com.cqupt.mis.erp.service.order.OrderPredictionBasicService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("orderPredictionBasicService")
public class OrderPredictionBasicServiceImpl implements OrderPredictionBasicService{
	
	@Resource
	private OrderPredictionBasicDao orderPredictionBasicDao;
	
	@Override
	public List<OrderPredictionBasic> findAllOrderPredictionBasic() {
		return orderPredictionBasicDao.findAllOrderPredictionBasic();
	}

}
