package com.cqupt.mis.erp.service.registerlogin.impl;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.registerlogin.ApprovedUserService;
import com.cqupt.mis.erp.service.registerlogin.RegisterInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 杨青 on 2016/9/4.
 */
@Service("approvedUserService")
public class ApprovedUserServiceImpl implements ApprovedUserService {
    @Resource
    private ApprovedUserDao approvedUserDao;
    @Resource
    private RegisterInfoService registerInfoService;

    @Override
    public String findUsernameByUserUnique(String userUnique) {
        String userName = approvedUserDao.findUsernameByUserUnique(userUnique);
        return userName;
    }

    @Override
    public List<ApprovedUserInfo> showApprovedUserList() {
        return approvedUserDao.findAllApprovedUserList();
    }

    @Override
    public boolean updatePassUser(String registerId) {
        try {
            RegisterInfo registerInfo;
            registerInfo = registerInfoService.findByUserId(registerId);
            String typeId = "1";

            approvedUserDao.addRegister(registerInfo.getUserID(), registerInfo.getName(), registerInfo.getPassword(),
                    registerInfo.getMajor(), registerInfo.getClassName(), registerInfo.getStudentID(),
                    registerInfo.getEmail(), registerInfo.getTel(), typeId);
            registerInfoService.deleteRegisterById(registerId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteApprovedUserById(String approvedUserId) {
        int result;
        try {
            result = approvedUserDao.deleteApprovedUserById(approvedUserId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return result == 1;
    }

    @Override
    public boolean updateApprovedUserInfo(ApprovedUserInfo approvedUserInfo) {
        String userId = approvedUserInfo.getUserID();
        String name = approvedUserInfo.getName();
        String password = approvedUserInfo.getPassword();
        String major = approvedUserInfo.getMajor();
        String className = approvedUserInfo.getClassName();
        String studentId = approvedUserInfo.getStudentID();
        String email = approvedUserInfo.getEmail();
        String tel = approvedUserInfo.getTel();

        int result = approvedUserDao.updateApprovedUserInfo(userId, name, password, major,
                className, studentId, email, tel);

        return result > 0;
    }
}
