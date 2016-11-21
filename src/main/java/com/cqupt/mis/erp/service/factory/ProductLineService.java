package com.cqupt.mis.erp.service.factory;

import com.cqupt.mis.erp.model.factory.ProductLineBasic;
import com.cqupt.mis.erp.model.factory.ProductLineCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;

import java.util.List;

public interface ProductLineService {
	/**
	 * 获取进入厂房内部所拥有的生产线信息。
	 * @author zy
	 * @param userUnique
	 * @param factoryId
	 * @return List<ProductLineInfo>
	 */
	List<ProductLineInfo> findProductLines(String userUnique, String factoryId);
	
	/**
	 * findProductLineInfo 更新某一条生产线的信息
	 * @param productLineId
	 * @return 
	 *List<ProductLineInfo>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	ProductLineInfo findProductLineInfo(String productLineId);

	/**
	 * 查看生产线详细信息
	 * @param userUnique
	 * @param productLineId
	 * @return
     */
	ProductLineCommonInfo findProductLineDetail(String userUnique, String productLineId);
	
	/**
	 * findProductLineDetailExceptStatus 没有status的中文值.
	 * @param userUnique
	 * @param productLineId
	 * @return 
	 *ProductLineCommonInfo
	 * @exception 
	 * @since  1.0.0
	 */
	ProductLineCommonInfo findProductLineDetailExceptStatus(String userUnique, String productLineId);

	/**
	 * 修改之后的方法.直接返回basic 生产线的固有属性
	 * @param productLineId
	 * @return
     */
	ProductLineBasic findProductLineBasic(String productLineId);

	/**
	*
	* 下面是进入“购买生产线”的时候，获取生产线基本信息表（PRODUCTLINEBASIC）里面所拥有的
	* 生产线类型 PRODUCTLINETYPE
    * 安装周期： SetupPeriod
	* 每期安装费用：SetupPeriodPrice
	* 转产周期：ChangePeriod
	* 每期转产费用：ChangeCost
	* 生产效率：ProducePeriod
	* 每期维护费用：MainCost
	* 每期折旧费用：Depreciation
	* 残值数额：StumpCost
	* 
	 * @author zy  
	 * @param
	 * @return
	 */
	List<ProductLineBasic> findProductLineBasics();

	/**
	 * 检查是否可以增加生产线。比较厂房容纳量和已有生产线数量
	 * @param userUnique
	 * @param factoryId
     * @return
     */
	boolean findIsAddProductLine(String userUnique, String factoryId);
	
	/**
	 * 增加生产线，1为添加成功，0为添加不成功
	 * @author zy
	 * @param userUnique
	 * @param factoryId
	 * @param productlineinfo
	 * @return 0:添加未成功； 1：添加成功； 2：容量已满，不能新增生产线
	 */
	int addProductLine(String userUnique, String factoryId, ProductLineCommonInfo productlineinfo);
	
	/**
	 * 查看生产线状态
	 * 0 安装中
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return 状态值
	 * 0 安装中，1 暂停安装，2 生产中，3 暂停生产，4待生产，5 转产，6 暂停转产
	 */
	int findProductLineStatus(String userUnique, String productLineId);


	/**
	 * 出售生产线
	 * @param userUnique
	 * @param productLineId
	 * @param productLineType
     */
	void deleteSaleProductLine(String userUnique, String productLineId, String productLineType);

	
	/**
	 * 开始生产
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateStartProduct(String userUnique, String productLineId);
	
	/**
	 * 恢复生产
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateReStartProduct(String userUnique, String productLineId);
	
	/**
	 * 暂停生产
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateStopProduct(String userUnique, String productLineId);
	
	/**
	 * 开始安装
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateRestall(String userUnique, String productLineId);
	
	/**
	 * 暂停安装
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateStopstall(String userUnique, String productLineId);
	
	/**
	 * 检查转产是否完成
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean findTransferIsFinished(String userUnique, String productLineId);

    /**
     * 开始转产
     * @param userUnique
     * @param productLineId
     * @param productName
     * @return
     */
	boolean updateStartChangeProduct(String userUnique, String productLineId, String productName);
	
	/**
	 * 继续转产
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateReChangeProduct(String userUnique, String productLineId);

	/**
	 * 暂停转产
	 * @author zy
	 * @param userUnique
	 * @param productLineId
	 * @return
	 */
	boolean updateStopChangeProduct(String userUnique, String productLineId);
	
	/**
	 * 撤销生产线
	 * @author 杨青
	 * @param userUnique
	 * @param productLineId
	 */
	
	boolean withdrawProductLine(String userUnique, String productLineId);
}
