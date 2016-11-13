package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("allOrdersOfGroupDao")
public interface AllOrdersOfGroupDao {
    /**
     * 添加所有以初始化好了的订单
     * @param allOrdersOfGroups
     */
    // TODO: 2016/8/18 要想办法重构阿
    void addAllOrdersOfGroups(ArrayList<AllOrdersOfGroup> allOrdersOfGroups);

    /**
     * 取出所有
     * @param groupName
     * @return
     */
    List<AllOrdersOfGroup> findAllOrdersOfGroup(String groupName);

    /**
     * 更新订单的特殊需求（ISO认证）
     * @param allOrdersOfGroups
     * @return
     */
    // TODO: 2016/8/18 要想办法重构阿 
    int updateSpecalRems(List<AllOrdersOfGroup> allOrdersOfGroups);

    /**
     * 根据orderId来查找订单的状态, 如果是null返回-1
     * @param orderId
     * @return
     */
    Integer findOrderStatusByOrderId(String orderId);

    /**
     * 根据orderId来改变状态值
     * @param orderId
     * @param status
     * @return
     */
    int updateOrderStatusByOrderId(@Param("orderId") String orderId,
                                   @Param("status") Integer status);

    /**
     * 根据orderId来直接查找对应整个allordersofgroup 实体类出来
     * @param orderId
     * @return
     */
    AllOrdersOfGroup findAllOrdersOfGroupByOrderId(String orderId);

    /**
     * 查询所有该期的订单.并且是能选的订单 既 status 为0
     * @param userUnique
     * @return
     */
    List<AllOrdersOfGroup> findOrderOfGroupByUserUnique(String userUnique);
}
