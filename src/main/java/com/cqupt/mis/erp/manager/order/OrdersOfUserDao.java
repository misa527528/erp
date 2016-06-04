package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.order.OrderSequence;
import com.cqupt.mis.erp.model.order.OrdersOfUser;

import java.util.List;

public interface OrdersOfUserDao extends BaseDao {
    /**
     * @param userUnique
     * @return
     * @author LX
     * 取出用户的所有订单byUserUnique
     */
    public List<OrdersOfUser> findOrdersOfUserByUserUnique(String userUnique);

    /**
     * @param userUnique
     * @return
     * @author LX
     * 取出已交货的订单
     */
    public List<OrdersOfUser> findDeliveredOrdersOfUser(String userUnique);

    /**
     * @param userUnique
     * @return
     * @author LX
     * 取出还未交货的订单
     */
    public List<OrdersOfUser> findNotDeliverOrdersOfUser(String userUnique);


    /**
     * findOrdersMoneyOfUser
     * 计算所有未破产用户在上一年中在该市场内对该产品进行   按时  交货的订单金额总和(设为A)
     *
     * @param marketName
     * @param productName
     * @param begin
     * @param end
     * @param groupName
     * @return List<OrderSequence>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<OrderSequence> findOrdersMoneyOfUser(String marketName, String productName, Integer begin, Integer end, String groupName);

    /**
     * findUnOrdersMoneyOfUser
     * 计算所有未破产用户在上一年中在该市场内对该产品进行  未按时  交货的订单金额总和（设为B）
     *
     * @param marketName
     * @param productName
     * @param begin
     * @param end
     * @param groupName
     * @return List<OrderSequence>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<OrderSequence> findUnOrdersMoneyOfUser(String marketName, String productName, Integer begin, Integer end, String groupName);

    /**
     * 取出最短交货订单的时间
     *
     * @return
     * @author LX
     */
    public int findSmallestTime(String userUnique);

    /**
     * 取出用户某个详细的订单
     *
     * @author LX
     */
    public OrdersOfUser findOrderDetail(String orderID);

    /**
     * 交货时的修改订单状态
     *
     * @author LX
     */
    public boolean updateOrderEndTime(OrdersOfUser ordersOfUser);

    /**
     * 取出订单销售金额
     *
     * @author LX
     */
    public float findSaleRoom(String ordersID);


}
