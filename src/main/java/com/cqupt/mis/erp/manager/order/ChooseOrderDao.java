package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.order.ChooseOrder;
import com.cqupt.mis.erp.model.vo.ChooseOrderVO;

import java.util.List;

public interface ChooseOrderDao extends BaseDao {

    /**
     * addChooseOrder 加入选择订单的顺序的数据
     *
     * @param userUnique
     * @param period
     * @param marketName
     * @param productName
     * @param groupName
     * @param sequence    void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void addChooseOrder(String userUnique, Integer period, String marketName, String productName, String groupName, Integer sequence);

    /**
     * updateChooseOrderValue 更新选单顺序.chooseValue 大于10000 之后就是剔除用户不在序列当中.
     *
     * @param userUnique
     * @param period
     * @param chooseValue void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateChooseOrderValue(String userUnique, Integer period, Integer chooseValue, String productName, String marketName);

    /**
     * updateChooseOrderValue 根据orderId来改变choosevalue
     *
     * @param orderId void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateChooseOrderValue(String orderId, Integer chooseValue);

    /**
     * updateChooseOrderValue
     * 根据userunique 来找到所有的的chooserorder 选项然后 直接更新到 chooservalue 值.
     * 可以用来结束所有的市场
     *
     * @param chooseValue
     * @param userUnique  void
     * @throws
     * @author hhy
     */
    public void updateChooseOrderValue(Integer chooseValue, String userUnique);

    /**
     * findChooseOrderByMarketNameAndProductName 根据marketname 和 productname 来得到相应的订单信息.
     * 但是只是显示投过广告费用的那些市场.和投过广告的产品
     *
     * @param userUnique  用户的唯一标识
     * @param marketName  市场名称
     * @param productName 产品名称
     * @return List<ChooseOrderVO>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<ChooseOrderVO> findChooseOrderByMarketNameAndProductName(String userUnique, String marketName, String productName);

    /**
     * findChooseOrderUser
     *
     * @param marketName
     * @param productName
     * @param period
     * @param groupName
     * @return String
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public String findChooseOrderUser(String marketName, String productName, Integer period, String groupName);


    /**
     * findChooseOrderByUserUnique
     * 根据用户的信息来找到相应当前的周期能够选择订单的     市场 和 产品区域
     * 这个用在结束某个订货会的方法.
     *
     * @param userUnique
     * @return List<ChooseOrder>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<ChooseOrder> findChooseOrderByUserUnique(String userUnique);

    /**
     * 加入缓存刷新
     */
    public void refreshChooseOrderCache();

    public List<ChooseOrderVO> findChooseOrderByMarketNameAndProductNameCacheWithGroup(
            String gameGroupName, String marketName, String productName,
            String userUnique);
}
