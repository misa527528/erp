package com.cqupt.mis.erp.service.admin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AdminOperateService {

	/**
	 * 更新管理员密码
	 * @param adminID
	 * @param oldPWD
	 * @param newPWD
     * @return
     */
	boolean updatePWD(String adminID, String oldPWD, String newPWD);

	Map<String, Object> adminUserLogin(String adminID, String pwd, HttpServletRequest request);

}
