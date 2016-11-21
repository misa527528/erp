package com.cqupt.mis.erp.service.factory;

import com.cqupt.mis.erp.model.factory.FactoryBasicInfo;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.vo.FactoryVO;

import java.util.List;

public interface FactoryRentService {

	/**
	 * 显示租用厂房信息
	 * @author zy 
	 * @param userUnique
	 * @return List<FactoryCommonInfo>
	 */
	List<FactoryCommonInfo> findFactoryRents(String userUnique);

	
	/**
	 * 显示租用详细厂房信息
	 * @author zy 
	 * @param userUnique
	 *            ,factoryId
	 * @return List<FactoryCommonInfo>
	 */
	FactoryCommonInfo findRentDetail(String userUnique, String factoryId);

	/**
	 * 入“新租赁厂房”的时候，获取厂房基本信息表（FACTORYBASIC）里面所拥有的
	 * 厂房类型;租用该厂房时，每期需要交纳的租金;还需要等待多少周期该租赁的厂房才可以使用
	 * @author zy 
	 * @param factoryType
	 * @return List<FactoryCommonInfo>
	 */
	List<FactoryBasicInfo> findFactoryBasics(String factoryType);

	/**
	 * 增加“新租赁厂房”
	 * @param factoryCommonInfo
	 * @return
     */
	boolean addRentFactory(FactoryCommonInfo factoryCommonInfo);

	/**
	 * 停止租用厂房
	 * @author zy 
	 * @param userUnique
	 * @param factoryId
	 * @return 1:删除成功； 2：厂房内有生产线，不能停止租用； 3：删除失败
	 */
	int deleteRentedFactory(String userUnique, String factoryId);

	/**
	 * findFactoryRentVO 这个用户所有的租用厂房
	 * @param userUnique
	 * @return 
	 *List<FactoryVO>
	 * @exception 
	 * @since  1.0.0
	 */
	List<FactoryVO> findFactoryRentVO(String userUnique);
	
	/**
	 * findFactoryRentVO 租用厂房信息不是全部的,是根据市场来筛选的
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *List<FactoryCommonInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	List<FactoryVO> findFactoryRentVO(String userUnique, String marketName);
}
