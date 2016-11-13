package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.model.enterpriseevaluate.Products;
import com.cqupt.mis.erp.model.order.OrderSequence;
import com.cqupt.mis.erp.model.order.OrdersOfUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("ordersOfUserDao")
public interface OrdersOfUserDao {
    /**取出用户的所有订单byUserUnique
     *
     * @param userUnique
     * @return
     */
    List<OrdersOfUser> findOrdersOfUserByUserUnique(String userUnique);

    /**
     * 取出已交货的订单
     * @param userUnique
     * @return
     */
    List<OrdersOfUser> findDeliveredOrdersOfUserByUserUnique(String userUnique);

    /**
     * 取出还未交货的订单
     * @param userUnique
     * @return
     */
    List<OrdersOfUser> findNotDeliverOrdersOfUser(String userUnique);

    /**
     * 计算所有未破产用户在上一年中在该市场内对该产品进行按时交货的订单金额总和(设为A)
     * @param marketName
     * @param productName
     * @param begin
     * @param end
     * @param groupName
     * @return
     */
    List<OrderSequence> findOrdersMoneyOfUser(@Param("marketName") String marketName,
                                              @Param("productName") String productName,
                                              @Param("begin") Integer begin,
                                              @Param("end") Integer end,
                                              @Param("groupName")  String groupName);

    /**
     * 计算所有未破产用户在上一年中在该市场内对该产品进行未按时交货的订单金额总和（设为B）
     * @param marketName
     * @param productName
     * @param begin
     * @param end
     * @param groupName
     * @return
     */
    List<OrderSequence> findUnOrdersMoneyOfUser(@Param("marketName") String marketName,
                                                @Param("productName") String productName,
                                                @Param("begin") Integer begin,
                                                @Param("end") Integer end,
                                                @Param("groupName") String groupName);

    /**
     * 取出最短交货订单的时间
     * @param userUnique
     * @return
     */
    int findSmallestTime(String userUnique);

    /**
     * 交货时的修改订单状态
     * @param userUnique
     * @param orderID
     * @return
     */
    int updateOrderEndTime(@Param("userUnique") String userUnique,
                           @Param("orderID") String orderID);

    /**
     * 取出用户某个详细的订单
     * @param orderID
     * @return
     */
    OrdersOfUser findOrderDetail(String orderID);

    /**
     * 取出订单销售金额
     * @param ordersID
     * @return
     */
    float findSaleRoom(String ordersID);

    /**
     *
     * @param userUnique
     * @param orderID
     * @param productName
     * @param price
     * @param pNumber
     * @param marketName
     * @param needTime
     * @param moneyTime
     * @param penalPercent
     * @param specialRem
     * @param endTime null
     * @return
     */
    // TODO: 2016/8/24 添加测试
    int addOrdersOfUser(@Param("userUnique") String userUnique,
                        @Param("orderID") String orderID,
                        @Param("productName") String productName,
                        @Param("price") Double price,
                        @Param("pNumber") Integer pNumber,
                        @Param("marketName") String marketName,
                        @Param("needTime") Integer needTime,
                        @Param("moneyTime") Integer moneyTime,
                        @Param("penalPercent") Double penalPercent,
                        @Param("specialRem") String specialRem,
                        @Param("endTime") Integer endTime);

    double getUserOutput(@Param("userunique") String userunique,
                         @Param("firstPeriod") int firstPeriod,
                         @Param("lastPeriod") int lastPeriod);

    double getMemberSaleByProduct(@Param("userunique") String userunique,
                                  @Param("productName") String productName,
                                  @Param("firstPeriod") int firstPeriod,
                                  @Param("lastPeriod") int lastPeriod);

    // TODO: 2016/8/25 这里是不是改为getProductCost更好
    List<Products> getProducts(@Param("userunique") String userunique,
                               @Param("firstPeriod") int firstPeriod,
                               @Param("lastPeriod") int lastPeriod);

    List<Products> getProductsByProductName(@Param("userunique") String userunique,
                               @Param("productName") String productName,
                               @Param("firstPeriod") int firstPeriod,
                               @Param("lastPeriod") int lastPeriod);
}
