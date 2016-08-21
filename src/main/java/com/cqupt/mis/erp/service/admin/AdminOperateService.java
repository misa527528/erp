package com.cqupt.mis.erp.service.admin;

import java.util.Map;

public interface AdminOperateService {

	/**
	 * 更新管理员密码
	 * @param adminID
	 * @param oldPWD
	 * @param newPWD
     * @return
     */
	public boolean updatePWD(String adminID, String oldPWD, String newPWD);

	public Map<String, Object> adminUserLogin(String adminID, String pwd);

}
