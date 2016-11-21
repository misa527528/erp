package com.cqupt.mis.erp.service.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;

import java.util.List;

public interface FactoryMakingService {

	/**
	 * findAllFactoryType 在基础表中查找所有的factoryType
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<String> findAllFactoryType();
	
	/**
	 * 获取“显示管理在建厂房”要显示的数据信息
	 * @author zy
	 * @param userUnique
	 * @return List<FactoryCommonInfo>
	 */
	List<FactoryCommonInfo> findFactoryMakings(String userUnique);

	/**
	 * findFactoryMakings 查询已经在建的厂房  多加一个条件就是marketname
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *List<FactoryCommonInfo>
	 * @exception 
	 * @author hhy
	 */
	List<FactoryCommonInfo> findFactoryMakings(String userUnique, String marketName);
	
	/**
	 * 查看在建厂房的明细信息
	 * @author zy
	 * @param userUnique
	 *            ,factoryId
	 * @return FactoryCommonInfo
	 */
	FactoryCommonInfo findMakingDetail(String userUnique, String factoryId);

	/**
	 * 新建厂房
	 * @author zy
	 * @param userUnique
	 *            ,FactoryCommonInfo
	 * @return
	 */
	boolean addMakingFactory(FactoryCommonInfo factoryCommonInfo, String userUnique);

	/**
	 * 暂停修建厂房
	 * @author zy 
	 * @param userUnique
	 *            ,factoryId
	 * @return boolean
	 */
	boolean updateStatusToZero(String userUnique, int factoryId);

	/**
	 * 恢复修建厂房
	 * @author zy 
	 * @param userUnique
	 *            ,factoryId
	 * @return boolean
	 */
	boolean updateStatusToOne(String userUnique, int factoryId);
}
