package com.cqupt.mis.erp.service.admin.impl;

import com.cqupt.mis.erp.manager.admin.AdminUserDao;
import com.cqupt.mis.erp.model.admin.AdminUser;
import com.cqupt.mis.erp.service.admin.AdminOperateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("adminOperateService")
public class AdminOperateServiceImpl implements AdminOperateService {

    @Resource
	private AdminUserDao adminUserDao;

	@Override
	public boolean updatePWD(String adminID, String oldPWD, String newPWD) {
		AdminUser adminUsers = adminUserDao.findAdminUsersByAdminId(adminID);
		if (adminUsers != null){
			int result = adminUserDao.updatePWD(adminID, oldPWD, newPWD);
			if (result == 1){
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> adminUserLogin(String adminID, String pwd) {
		Map<String, Object> map = new HashMap<>();
		AdminUser adminUser = adminUserDao.findAdminUsersByAdminId(adminID);

		if (adminUser.equals("") || adminUser == null){
			map.put("status", 0);
			map.put("message", "该用户不存在");
		}
		else if ((adminUser.getAdminId().equals(adminID) && adminUser.getPassword().equals(pwd))){
			map.put("status", 1);
			map.put("message", "登录成功");
		}
		else {
			map.put("status", 0);
			map.put("message", "用户名或密码错误");
		}

		return map;
	}
}
