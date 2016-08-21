package com.cqupt.mis.erp.manager.inigame;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("iniGameDao")
public interface IniGameDao extends BaseDao{
	
	/**@author LX
	 * 初始化PLSQL存储函数
	 * @param groupName
	 * @return
	 */
	int iniGameByPLSQL(@Param("groupName") String groupName,
					   @Param("result") int result);
	
}
