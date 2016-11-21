package com.cqupt.mis.erp.service.registerlogin;


import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;

import java.util.List;

/**
 * 
 * RegisterInfoService
 * @author heinz_ho
 */
public interface RegisterInfoService {
	
	/**
	 * addRegister 添加新的注册用户
	 * @param registerInfo
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean addRegister(RegisterInfo registerInfo);
	
	/**
	 * modifyRegister 修改注册用户
	 * @param registerInfo
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean updatemodifyRegister(RegisterInfo registerInfo);
	
	/**
	 * modifyApprovedUser 修改已经通过修改的用户
	 * @param registerInfo
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean updateModifyApprovedUser(RegisterInfo registerInfo);
	
	/**
	 * 取出用户信息byUserID
	 * @author lx
	 * @param registerId
	 * @return
	 */
	RegisterInfo findByUserId(String registerId);

	/**
	 * findAllRegister 查找所有的注册用户, 
	 * @return 
	 *List<RegisterInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	List<RegisterInfo> findAllRegister();
	
	/**
	 * deleteRegisterById 删除注册用户
	 * @param registerId
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean deleteRegisterById(String registerId);

	/**
	 * 判断该userId是否已经存在
	 * @param userID
	 * @return
     */
	boolean isUserExist(String userID);
}
