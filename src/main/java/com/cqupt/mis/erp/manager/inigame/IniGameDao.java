package com.cqupt.mis.erp.manager.inigame;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.springframework.stereotype.Repository;

@Repository("iniGameDao")
public interface IniGameDao extends BaseDao {

    /**
     * @param groupName
     * @return
     * @author LX
     * 初始化PLSQL存储函数
     */
    public boolean iniGameByPLSQL(String groupName);

}
