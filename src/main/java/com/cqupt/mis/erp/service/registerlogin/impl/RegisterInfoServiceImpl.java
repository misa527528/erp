package com.cqupt.mis.erp.service.registerlogin.impl;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.RegisterUserDao;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.registerlogin.RegisterInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("registerInfoService")
public class RegisterInfoServiceImpl implements RegisterInfoService {
	
	@Resource
	private RegisterUserDao registerUserDao;
	@Resource
	private ApprovedUserDao approvedUserDao;
	
	@Override
	public boolean addRegister(RegisterInfo registerInfo) {
        String userId = registerInfo.getUserID();
        String userName = registerInfo.getName();
        String password = registerInfo.getPassword();
        String major = registerInfo.getMajor();
        String className = registerInfo.getClassName();
        String studentID = registerInfo.getStudentID();
        String email = registerInfo.getEmail();
        String tel = registerInfo.getTel();
        String status = registerInfo.getStatus();

		try {
			registerUserDao.addRegister(userId, userName, password, major, className, studentID, email, tel, status);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatemodifyRegister(RegisterInfo registerInfo) {
		try {
			String userID = registerInfo.getUserID();
			String name = registerInfo.getName();
			String password = registerInfo.getPassword();
			String major = registerInfo.getMajor();
			String className = registerInfo.getClassName();
			String studentID = registerInfo.getStudentID();
			String email = registerInfo.getEmail();
			String tel = registerInfo.getTel();

			registerUserDao.updateRegister(userID, name, password, major,className, studentID, email, tel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateModifyApprovedUser(RegisterInfo registerInfo) {
		try {
			String userID = registerInfo.getUserID();
			String name = registerInfo.getName();
			String password = registerInfo.getPassword();
			String major = registerInfo.getMajor();
			String className = registerInfo.getClassName();
			String studentID = registerInfo.getStudentID();
			String email = registerInfo.getEmail();
			String tel = registerInfo.getTel();

			approvedUserDao.updateApprovedUserInfo(userID, name, password, major, className, studentID, email, tel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public RegisterInfo findByUserId(String registerId) {
		return registerUserDao.findRegisterByUserId(registerId);
	}

	@Override
	public List<RegisterInfo> findAllRegister() {
		return registerUserDao.findAllRegister();
	}

	@Override
	public boolean deleteRegisterById(String registerId) {
		int result = registerUserDao.deleteRegisterById(registerId);

		return result > 0;
	}

    @Override
    public boolean isUserExist(String userID) {
        RegisterInfo register = registerUserDao.findRegisterByUserId(userID);
        ApprovedUserInfo approvedUser = approvedUserDao.findApprovedUserByUserId(userID);

        return (register!= null || approvedUser != null);
    }


}
