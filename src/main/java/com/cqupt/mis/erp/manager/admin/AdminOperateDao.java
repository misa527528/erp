package com.cqupt.mis.erp.manager.admin;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("adminOperateDao")
public interface AdminOperateDao extends BaseDao {

    public boolean updatePWD(String adminID, String oldPWD, String newPWD);

    public Map<String, Object> adminUserLogin(String adminID, String pwd);


}
