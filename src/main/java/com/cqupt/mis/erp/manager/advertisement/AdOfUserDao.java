package com.cqupt.mis.erp.manager.advertisement;

import com.cqupt.mis.erp.model.advertisement.Advertisement;
import com.cqupt.mis.erp.model.order.OrderSequence;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("adOfUserDao")
public interface AdOfUserDao {

    /**
     * 根据市场名称来找到相应广告单
     * @param marketName
     * @param userUnique
     * @return
     */
    List<Advertisement> findAdvertisementByMarketName(@Param("marketName") String marketName,
                                                      @Param("userUnique") String userUnique);

    /**
     * 提交广告费用,add 的方法是由那个初始化的方法加入
     * @param advertisementId
     * @param money
     * @return
     */
    int updateAdvertisementForProduct(@Param("advertisementId") Integer advertisementId,
                                      @Param("money") Double money);

    /**
     * 查找用户总共投放的广告费用
     * @param userUnique
     * @param period
     * @return
     */
    Double findSummaryMoney(@Param("userUnique") String userUnique, @Param("period") Integer period);

    /**
     * 根据某个市场,某个产品,某个组,某个周期 来返回一个投放广告的序列
     * @param groupName
     * @param period
     * @param marketName
     * @param productName
     * @return
     */
    List<OrderSequence> findAdvertisementMoneyOfUsers(@Param("marketNamegroupName") String groupName,
                                                      @Param("period") Integer period,
                                                      @Param("marketName") String marketName,
                                                      @Param("productName") String productName);

    /**
     * 查看用户在某个市场某个产品 里面投放广告费用的多少
     * @param userUnique
     * @param period
     * @param marketName
     * @param productName
     * @return
     */
    Double findUserAdvertisementMoney(@Param("userUnique") String userUnique,
                                      @Param("period") Integer period,
                                      @Param("marketName") String marketName,
                                      @Param("productName") String productName);

    /**
     * 取出所有市场名字和产品名称，用于初始化广告费的投放
     * @return
     * 其实这个方法是不应该出现在这里的，只是涉及到两张表联合查询，还没想好放哪里
     */
    List<Advertisement> findMarketnameAndProductname();

    /**
     * 添加广告初始化的list
     * @param list
     * @return
     */
    // TODO: 2016/8/15 可读性太差了，需要重构
    int addAdvertisementList(List<Advertisement> list);

    /**
     * 查找已经投放广告的 advertisement  period 已经封装在sql 里面了
     * @param userUnique
     * @return
     */
    List<Advertisement> findAlreadAdvertisement(String userUnique);

    /**
     * 投放过广告的市场
     * @param userUnique
     * @return
     */
    List<String> findMoneyIntoMarket(String userUnique);

    /**
     * 选择市场之后对应相应的产品搜索
     * @param userUnique
     * @param marketName
     * @return
     */
    List<String> findProductNameByMarket(@Param("userUnique") String userUnique,
                                         @Param("marketName") String marketName);
}
