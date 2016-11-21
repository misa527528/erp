package com.cqupt.mis.erp.service.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialPurchaseRecord;

import java.util.List;

public interface MaterialPurchaseService {
	/**
	 * 购买原材料，添加订单
	 * @author zy
	 * @return List<MaterialBasic>
	 * 
	 */
	boolean addMaterialPurchaseRecord(MaterialPurchaseRecord materialPurchaseRecord);
	/**
	 * 查看原材料订单
	 * @author zy
	 * @param userUnique
	 * @param pageSize
	 * @param status 区分已到货还是未到货的订单 0:已到货，1：未到货
	 * @param pageNumber
	 * @return
	 */
	List<MaterialPurchaseRecord> findMaterialOrders(String userUnique, int pageSize, int status, int pageNumber);
	
	/**
	 * 查询已到货订单总数
	 * @param userUnique
	 * @param currentTime
	 * @return
	 */
	int findMaterialOrdersReachCount(String userUnique, int currentTime);
	
	/**
	 * 查询未到货订单总数
	 * @param userUnique
	 * @param currentTime
	 * @return
	 */
	int findMaterialOrdersUnReachCount(String userUnique, int currentTime);
	
	
}
