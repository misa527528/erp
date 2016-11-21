package com.cqupt.mis.erp.service.advertisement;

public interface AdAndOrderRefreshService {
	/**
	 * getAdAndOrderFlag 获得一个flag 类似通知凭证的东西.
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void getAdAndOrderFlag();

	/**
	 * changeOrder 通知所有的正在选单的人.重新刷新你现在的选单的
	 * @param userUnique 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void changeOrder(String userUnique);
}
