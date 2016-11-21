package com.cqupt.mis.erp.service.order.impl;

import com.cqupt.mis.erp.manager.order.AllOrdersOfGroupDao;
import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;
import com.cqupt.mis.erp.service.order.AllOrdersOfGroupService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;




@Component("allOrdersOfGroupService")
public class AllOrdersOfGroupServiceImpl implements AllOrdersOfGroupService {
	
	@Resource
	private AllOrdersOfGroupDao allOrdersOfGroupDao;

	
	@Override
	public void addAllOrdersOfGroups(ArrayList<AllOrdersOfGroup> allOrdersOfGroups) {
		//System.out.println(allOrdersOfGroups.size());
		allOrdersOfGroupDao.addAllOrdersOfGroups(allOrdersOfGroups);		
	}

	@Override
	public List<AllOrdersOfGroup> findAllOrdersOfGroup(String groupName) {
		return allOrdersOfGroupDao.findAllOrdersOfGroup(groupName);
		
	}

	@Override
	public boolean updateSpecalRems(List<AllOrdersOfGroup> allOrdersOfGroups) {
		allOrdersOfGroupDao.updateSpecalRems(allOrdersOfGroups);
		return true;
	}

	@Override
	public List<AllOrdersOfGroup> findOrderOfGroupByUserUnique(String userUnique) {
		return allOrdersOfGroupDao.findOrderOfGroupByUserUnique(userUnique);
	}
	
}
