package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;

import java.util.ArrayList;
import java.util.List;

public interface AllOrdersOfGroupDao extends BaseDao {
    /**
     * @param allOrdersOfGroups
     * @author LX
     * 添加所有以初始化好了的订单
     */
    public void addAllOrdersOfGroups(ArrayList<AllOrdersOfGroup> allOrdersOfGroups);

    /**
     * @param groupName
     * @return
     * @author LX
     * 取出所有
     */
    public List<AllOrdersOfGroup> findAllOrdersOfGroup(String groupName);

    /**
     * @param allOrdersOfGroups
     * @return
     * @author LX
     * 更新订单的特殊需求（ISO认证）
     */
    public boolean updateSpecalRems(List<AllOrdersOfGroup> allOrdersOfGroups);

    /**
     * findOrderStatusByOrderId 根据orderId来查找订单的状态
     *
     * @param orderId
     * @return Integer
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public Integer findOrderStatusByOrderId(String orderId);

    /**
     * updateOrderStatusByOrderId 根据orderId来改变状态值
     *
     * @param orderId
     * @return boolean
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public boolean updateOrderStatusByOrderId(String orderId, Integer status);

    /**
     * findAllOrdersOfGroupByOrderId 根据orderId来直接查找对应整个allordersofgroup 实体类出来
     *
     * @param orderId
     * @return AllOrdersOfGroup
     * @throws
     * @since 1.0.0
     */
    public AllOrdersOfGroup findAllOrdersOfGroupByOrderId(String orderId);

    /**
     * findOrderOfGroupByUserUnique 查询所有该期的订单.并且是能选的订单 既 status 为0
     *
     * @param userUnique
     * @return List<AllOrdersOfGroup>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<AllOrdersOfGroup> findOrderOfGroupByUserUnique(String userUnique);
}
