package com.cqupt.mis.erp.service.advertisement;

import com.cqupt.mis.erp.model.advertisement.Advertisement;

import java.util.List;

public interface AdvertisementService {
	
	/**
	 * findAdvertisementByMarketName 根据userUnique 来找到某个已经开拓市场下面的产品
	 * 第二次改版之后能够显示已开拓的市场的4种产品,忽略他是否已经开发的产品了.
	 * @param marketName
	 * @param userUnique
	 * @return 
	 *List<Advertisement>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<Advertisement> findAdvertisementByMarketName(String marketName, String userUnique);
	
	/**
	 * findAlreadyAdvertisement 确认投放广告的列表
	 * @param userUnique
	 * @return 
	 *List<Advertisement>
	 * @exception 
	 * @since  1.0.0
	 */
	List<Advertisement> findAlreadyAdvertisement(String userUnique);
	
	/**
	 * 
	 * updateAdvertisementForProduct 提交广告费对某个市场的某个产品的广告费.
	 * @param advertisementId
	 * @param money
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	boolean updateAdvertisementForProduct(int advertisementId, double money);
	
	/**
	 * updateAdvertisementFinish 投放广告完成的时候.
	 * 1-不成功.投放的钱太多.不能投了.
	 * 2-成功.
	 * 3-已经进行过结束广告费投放操作
	 * 
	 * @param userUnique
	 * @return 
	 * 		一个状态值
	 * @exception 
	 * @since  1.0.0
	 */
	int updateAdvertisementFinish(String userUnique);

	/**
	 * 年初初始化用户需要投放的广告费的条目
	 * @param userUnique
	 * @author hhy
	 */
	void initAdOfUser(String userUnique);

    /**
     * 选择市场之后对应相应的产品搜索
     * @param userUnique
     * @param marketName
     * @return
     */
	List<String> findProductNameByMarket(String userUnique,String marketName);

    List<String> findMoneyIntoMarket(String userUnique);
}
