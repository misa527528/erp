package com.cqupt.mis.erp.manager.order;

import com.cqupt.mis.erp.model.order.ChooseOrder;
import com.cqupt.mis.erp.model.vo.ChooseOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("chooseOrderDao")
public interface ChooseOrderDao {
    /**
     * 加入选择订单的顺序的数据
     *
     * @param userUnique
     * @param period
     * @param marketName
     * @param productName
     * @param groupName
     * @param sequence
     * @return
     */
    int addChooseOrder(@Param("userUnique") String userUnique,
                       @Param("period") Integer period,
                       @Param("marketName") String marketName,
                       @Param("productName") String productName,
                       @Param("groupName") String groupName,
                       @Param("sequence") Integer sequence);

    /**
     * 更新选单顺序.chooseValue 大于10000 之后就是剔除用户不在序列当中.
     *
     * @param userUnique
     * @param period
     * @param chooseValue
     * @param productName
     * @param marketName
     * @return
     */
    @CacheEvict(value = "ChooseOrderVO", allEntries = true, beforeInvocation = true)
    int updateChooseOrderValue(@Param("userUnique") String userUnique,
                               @Param("period") Integer period,
                               @Param("chooseValue") Integer chooseValue,
                               @Param("productName") String productName,
                               @Param("marketName") String marketName);

    /**
     * 根据orderId来改变choosevalue
     *
     * @param orderId
     * @param chooseValue
     * @return
     */
    @CacheEvict(value = "ChooseOrderVO", allEntries = true, beforeInvocation = true)
    int updateChooseOrderValueByOrderId(@Param("orderId") String orderId,
                                        @Param("chooseValue") Integer chooseValue);

    /**
     * 根据userunique 来找到所有的的chooserorder 选项然后 直接更新到 chooservalue 值.
     *
     * @param chooseValue
     * @param userUnique
     * @return
     */
    @CacheEvict(value = "ChooseOrderVO", allEntries = true, beforeInvocation = true)
    int updateChooseOrderValueByUserUnique(@Param("chooseValue") Integer chooseValue,
                                           @Param("userUnique") String userUnique);

    /**
     * @param marketName
     * @param productName
     * @param period
     * @param groupName
     * @return
     */
    String findChooseOrderUser(@Param("marketName") String marketName,
                               @Param("productName") String productName,
                               @Param("period") Integer period,
                               @Param("groupName") String groupName);

    /**
     * 根据用户的信息来找到相应当前的周期能够选择订单的市场和产品区域
     * 这个用在结束某个订货会的方法.
     *
     * @param userUnique
     * @return
     */
    List<ChooseOrder> findChooseOrderByUserUnique(String userUnique);

    /**
     * 加入缓存刷新
     * 清空缓存
     */
    @CacheEvict(value = "ChooseOrderVO", allEntries = true, beforeInvocation = true)
    void refreshChooseOrderCache();

    /**
     * 这里的缓存如果还有问题， 就直接注释了。
     * 注意：调用该方法时，如果marketName或productName为空的话就用all代替
     * @param period
     * @param gameGroupName
     * @param marketName
     * @param productName
     * @param userUnique
     * @return
     */
    // @Cacheable(value = {"ChooseOrderVO"}, key = "#period+#gameGroupName+#marketName+#productName")
    List<ChooseOrderVO> findChooseOrderByMarketNameAndProductNameCacheWithGroup(
                    @Param("period") int period,
                    @Param("groupName") String gameGroupName,
                    @Param("marketName") String marketName,
                    @Param("productName") String productName,
                    @Param("userUnique") String userUnique);

}
