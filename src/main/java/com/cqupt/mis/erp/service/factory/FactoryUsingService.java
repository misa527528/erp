package com.cqupt.mis.erp.service.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.vo.FactoryVO;

import java.util.List;

public interface FactoryUsingService {

	/**
	 * findFactoryUsing 提供前台的VO对象
	 * @param userUnique
	 * @return 
	 *List<FactoryVO>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<FactoryVO> findFactoryUsing(String userUnique);
	
	/**
	 * findFactoryUsing 根据前台给的信息查找相应的工厂信息
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *List<FactoryVO>
	 * @exception 
	 * @since  1.0.0
	 */
	List<FactoryVO> findFactoryUsing(String userUnique, String marketName);
	
	
	/**
	 * 获取“管理建造好厂房的操作界面”要显示的数据信息
	 * @author zy 
	 * @param userUnique
	 * @return List<FactoryCommonInfo>
	 */
	List<FactoryCommonInfo> findFactoryUsings(String userUnique);

	/**
	 * 查看厂房明细信息
	 * @author zy 
	 * @param userUnique
	 *            ,factoryId
	 * @return List<FactoryCommonInfo>
	 */
	FactoryCommonInfo findUsingDetail(String userUnique, String factoryId);
	/**
	 * 判断是否可以出售厂房
	 * @author zy 
	 * @param userUnique
	 * @param factoryId
	 * @return 
	 * 
	 */
	boolean findisSale(String userUnique, String factoryId);
	/**
	 * 出售厂房
	 * @author zy
	 * @param userUnique
	 * @param factoryId
	 * @return
	 */
	void deleteSaleUsingFactory(String userUnique, String factoryId);

}
