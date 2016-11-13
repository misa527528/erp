package com.cqupt.mis.erp.manager.inigame;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("iniGameDao")
// TODO: 2016/8/31 这个类的方法都需要测试
public interface IniGameDao extends BaseDao{
	
	/**@author LX
	 * 初始化PLSQL存储函数
	 * @param groupName
	 * @return
	 */
	int iniGameByPLSQL(@Param("groupName") String groupName,
					   @Param("result") int result);

    /**
     * 下一周期的存储函数调用
     * @param userUnique
     * @param result===0
     * @return
     */
    int runFunctionInOracle(@Param("userUnique") String userUnique,
                            @Param("result") int result);
	
}
