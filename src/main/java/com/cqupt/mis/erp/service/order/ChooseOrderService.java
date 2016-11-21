package com.cqupt.mis.erp.service.order;

import com.cqupt.mis.erp.model.order.ChooseOrder;
import com.cqupt.mis.erp.model.vo.ChooseOrderVO;

import java.util.List;

public interface ChooseOrderService {
	
	/**
	 * 
	 * updateChoosingOrder 
	 * 正在选择的订单的操作.  
	 * @param userUnique
	 * @param orderId
	 * @return 
	 *int
	 * 根据返回不同的 数字来判断是是  返回的是什么状态
	 * 1-成功选择							</br>
	 * 2-还没有轮到你选的说.					</br>
	 * 3-iso没有研发成功					</br>
	 * 4-市场没有开发						</br>
	 * 5-这张订单已经被取走了.					</br>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	int updateChoosingOrder(String userUnique, String orderId);
	
	/**
	 * updateEndChooseOrder  结束所有的订货会
	 * @param userUnique
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	int updateEndChooseOrder(String userUnique);
	
	/**
	 * updateChooseOrderValue 
	 * 结束掉某个市场某个产品的 订单会 即退出这个订货会
	 * @param orderId
	 * @param chooseValue 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void updateChooseOrderValue(String orderId, Integer chooseValue);
	
	/**
	 * findAllChooseOrderByUserUnique 
	 * 		      查找所有的的chooserorder信息 在结束某个市场的时候使用
	 * @param userUnique
	 * @return 
	 *List<ChooseOrder>
	 * @exception 
	 * @since  1.0.0
	 */
	List<ChooseOrder> findAllChooseOrderByUserUnique(String userUnique);

	/**
	 * udpateIsClooseAll 检查是否已经选择完全部.如果是就直接结束掉
	 * @param userUnique 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	boolean udpateIsClooseAll(String userUnique);

	List<ChooseOrderVO> findChooseOrderByMarketNameAndProductName(String userUnique, String marketName, String productName);
}
