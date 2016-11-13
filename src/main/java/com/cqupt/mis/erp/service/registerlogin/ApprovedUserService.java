package com.cqupt.mis.erp.service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;

import java.util.List;

/**
 * Created by 杨青 on 2016/9/4.
 */
public interface ApprovedUserService {
    String findUsernameByUserUnique(String userUnique);

    /**
     * 取出所有用户
     * @return
     */
    List<ApprovedUserInfo> showApprovedUserList();

    /**
     * 通过用户的申请
     * @param registerId
     * @return
     */
    boolean updatePassUser(String registerId);

    /**
     * 删除用户
     * @param approvedUserId
     * @return
     */
    boolean deleteApprovedUserById(String approvedUserId);

    /**
     * 修改用户信息
     * @param approvedUserInfo
     * @return
     */
    boolean updateApprovedUserInfo(ApprovedUserInfo approvedUserInfo);
}
