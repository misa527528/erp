package com.cqupt.mis.erp.manager.advertisement;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.advertisement.Advertisement;
import com.cqupt.mis.erp.model.order.OrderSequence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("advertisementDao")
public interface AdvertisementDao extends BaseDao {

    /**
     * findAdvertisementByMarketName 根据市场名称来找到相应广告单
     *
     * @param marketName
     * @param userUnique
     * @return List<Advertisement>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<Advertisement> findAdvertisementByMarketName(String marketName, String userUnique);

    /**
     * updateAdvertisementForProduct 提交广告费用,add 的方法是由那个初始化的方法加入
     *
     * @param advertisementId
     * @param money           void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateAdvertisementForProduct(Integer advertisementId, Double money);

    /**
     * findSummaryMoney 查找用户总共投放的广告费用
     *
     * @param userUnique
     * @param period
     * @return Double
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public Double findSummaryMoney(String userUnique, Integer period);

    /**
     * findAdvertisementMoneyOfUsers
     * 根据某个市场,某个产品,某个组,某个周期 来返回一个投放广告的序列
     *
     * @param groupName
     * @param period
     * @param marketName
     * @param productName
     * @return List<OrderSequence>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<OrderSequence> findAdvertisementMoneyOfUsers(String groupName, Integer period, String marketName, String productName);

    /**
     * findUserAdvertisementMoney
     * 查看用户在某个市场某个产品 里面投放广告费用的多少
     *
     * @param userUnique
     * @param period
     * @param marketName
     * @param productName
     * @return Double
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public Double findUserAdvertisementMoney(String userUnique, Integer period, String marketName, String productName);

    /**
     * 取出所有市场名字和产品名称，用于初始化广告费的投放
     *
     * @author lx
     */
    public List<Advertisement> findMarketnameAndProductname();

    /**
     * 添加广告初始化的list
     *
     * @author lx
     */
    public boolean addAdvertisementList(List<Advertisement> list);

    /**
     * findAlreadAdvertisement
     * 查找已经投放广告的 advertisement  period 已经封装在sql 里面了
     *
     * @param userUnique
     * @return List<Advertisement>
     * @throws
     * @since 1.0.0
     */
    public List<Advertisement> findAlreadAdvertisement(String userUnique);

    /**
     * findMoneyIntoMarket 投放过广告的市场
     *
     * @param userUnique
     * @return List<String>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<String> findMoneyIntoMarket(String userUnique);

    /**
     * findProductNameByMarket 选择市场之后对应相应的产品搜索
     *
     * @param userUnique
     * @param marketName
     * @return List<String>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<String> findProductNameByMarket(String userUnique, String marketName);
}
