package com.cqupt.mis.erp.service.admin.impl;

import com.cqupt.mis.erp.manager.admin.AdminUserDao;
import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.admin.AdminUser;
import com.cqupt.mis.erp.service.admin.AdminOperateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
	public Map<String, Object> adminUserLogin(String adminID, String pwd, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		AdminUser adminUser = adminUserDao.findAdminUsersByAdminId(adminID);

		if (adminUser == null || "".equals(adminUser)){
			map.put("status", ReturnStatus.ERROR);
			map.put("message", "该用户不存在");
			map.put("data", null);
		}
		else if ((adminUser.getAdminId().equals(adminID)
				&& adminUser.getPassword().equals(pwd))){
            request.setAttribute("adminId", adminID);
			map.put("status", ReturnStatus.ADMINPAGE);
			map.put("message", "登录成功");
			map.put("data", adminUser);
		}
		else {
			map.put("status", ReturnStatus.ERROR);
			map.put("message", "用户名或密码错误");
			map.put("data", null);
		}

		return map;
	}
}
