package com.cqupt.mis.erp.service.prediction.impl;

import com.cqupt.mis.erp.manager.prediction.PredictionOfGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.prediction.PredictionOfGroup;
import com.cqupt.mis.erp.model.vo.PredictionOfGroupVO;
import com.cqupt.mis.erp.service.prediction.PredictionOfGroupService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("predictionOfGroupService")
public class PredictionOfGroupServiceImpl implements PredictionOfGroupService{
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private PredictionOfGroupDao predictionOfGroupDao;
    @Resource
    private GameGroupDao gameGroupDao;
	
	@Override
	public void addPredictionOfGroup(PredictionOfGroup predictionOfGroup) {
		String groupName = predictionOfGroup.getGroupName();
		Integer period = predictionOfGroup.getPeriod();
		String marketName = predictionOfGroup.getMarketName();
		String productName = predictionOfGroup.getProductName();
		Double price = predictionOfGroup.getPrice();
		Integer mount = predictionOfGroup.getMount();

		predictionOfGroupDao.addPredictionOfGroup(groupName, period, marketName, productName, price, mount);
	}

	@Override
	public List<PredictionOfGroup> findAllPredictionOfGroup(String groupName) {
		return predictionOfGroupDao.findAllPredictionOfGroup(groupName);
	}

	@Override
	public boolean addPredictionOfGroups(List<PredictionOfGroup> predictionOfGroups) {
		int result = predictionOfGroupDao.addPredictionOfGroups(predictionOfGroups);

        return result > 0;
	}

	@Override 
	//TODO 考虑重构
	public Map<String, Object> findPredictionOfGroupByMarketName(String marketName, String userUnique) {
		String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		//先取出所有预测信息
		List<PredictionOfGroupVO> li =  predictionOfGroupDao.findPredictionOfGroupByMarketName(marketName, groupName);
		//准备装入的集合
		Map<String, Object> map = new HashMap<>(5);
		String productName = "";
		//游戏总年数
		int year = gameGroupDao.findTotalYear(userUnique);
		//每种产品各年的预测量装入该集合(重复使用该集合装4种产品)
		List<Integer> list;
		for(int n=0; n<4; n++){
			list = new ArrayList<>();
			for(int i=0; i<(li.size()/4); i++) {
				//根据期数不同，取出相应的信息，period实质上是数据库取出的信息的序列号，因为从数据库取出信息是排好序的。
				int period = i+year*n;
				productName = li.get(period).getProductName();
				list.add(li.get(period).getMount());	
			}   
			map.put(productName, list);
		}

		Integer min=99999;
		Integer max=0;
		for(int m=0; m<li.size(); m++) {
			if(min > li.get(m).getMount()){
				min = li.get(m).getMount();
			}
			if(max < li.get(m).getMount()){
				max = li.get(m).getMount();
			}
		}
		map.put("mountMin", min);
		map.put("mountMax", max);

		Map<String, Object> map2 = new HashMap<String, Object>(5);
		List<Float> list2;
		for(int n=0; n<4; n++){
			list2 = new ArrayList<Float>();
			for(int i=0; i<(li.size()/4); i++) {
				int period = i+year*n;
				productName = li.get(period).getProductName();
				list2.add(li.get(period).getPrice());
			}   
			map2.put(productName, list2);
		}
		Float min2=99999f;
		Float max2=0f;
		for(int m=0; m<li.size(); m++) {
			if(min2 > li.get(m).getPrice()){
				min2 = li.get(m).getPrice();
			}
			if(max2 < li.get(m).getPrice()){
				max2 = li.get(m).getPrice();
			}
		}
		map2.put("priceMin", min2);
		map2.put("priceMax", max2);
		
		Map<String,Object> map3 = new HashMap<String,Object>(2);
		map3.put("mountMap", map);
		map3.put("priceMap", map2);
		
		map3.put("marketName", marketName);
		return map3;
	}
}
