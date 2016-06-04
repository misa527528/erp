package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.order.OrderPredictionBasic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderPredictionBasicDao")
public interface OrderPredictionBasicDao extends BaseDao {
    /**
     * @return
     * @author LX
     * 取出所有最基础的订单信息
     */
    public List<OrderPredictionBasic> findAllOrderPredictionBasic();
}
