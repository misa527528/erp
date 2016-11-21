package com.cqupt.mis.erp.service.inigame;

public interface IniGameService {
	/**
	 * 所有注释在实现该接口类中注明！
	 * @author LX 
	 */
	boolean iniGameByPLSQL(String groupName);
	
	boolean initPredictionOfGroup(String groupName);
	
	boolean iniGroupOrder(String groupName);
	
	boolean iniOrderISO(String groupName);
		
	boolean iniGame(String groupName);
	
	boolean updateUserUnqiqueAndCurrentPeriod(String groupName);
}
