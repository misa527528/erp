package com.cqupt.mis.erp.manager.prediction;

import com.cqupt.mis.erp.model.order.OrderPredictionBasic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("orderPredictionBasicDao")
public interface OrderPredictionBasicDao {
    /**
     * 取出所有最基础的订单信息
     * @return
     */
    List<OrderPredictionBasic> findAllOrderPredictionBasic();
}
