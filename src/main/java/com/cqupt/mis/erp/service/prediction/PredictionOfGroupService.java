package com.cqupt.mis.erp.service.prediction;

import com.cqupt.mis.erp.model.prediction.PredictionOfGroup;

import java.util.List;
import java.util.Map;

public interface PredictionOfGroupService {
	
	/**
	 * 添加市场预测信息
	 * @author lx
	 * @param predictionOfGroup
	 */
	void addPredictionOfGroup(PredictionOfGroup predictionOfGroup);
	
	/**
	 * 取出所有市场预测信息
	 * @author lx
	 * @param groupName
	 * @return
	 */
	List<PredictionOfGroup> findAllPredictionOfGroup(String groupName);

	/**
	 * 批量添加市场预测信息
	 * @param predictionOfGroups
	 * @return
     */
	boolean addPredictionOfGroups(List<PredictionOfGroup> predictionOfGroups);
	
	/**
	 * 根据市场名称取出相应的预测信息
	 * @param marketName
	 * @return
	 */
	Map<String,Object> findPredictionOfGroupByMarketName(String marketName, String userUnique);
	
}
