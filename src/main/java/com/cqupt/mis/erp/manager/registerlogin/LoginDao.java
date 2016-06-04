package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;

/**
 * Created by yangqing on 2016/6/4.
 */
public interface LoginDao {
    public RegisterInfo findRegisterByUserId(String userId);

    public ApprovedUserInfo findApprovedUserByUserId(String userId);

    public GameGroupMemberInfo findGroupMemberByUserId(String userId);

    public GameGroupInfo findUserUniqueInGroupMemberByUserId(String userId);

    /**
     * getStatusByUserUnique 获取这个正在游戏的用户的相关情况
     * 0: 表示破产
     * 1: 游戏正在进行
     * 2: 用户已经完成游戏周期
     *
     * @param userUnique
     * @return
     */
    public int getStatusByUserUnique(String userUnique);

    public int findGameCreatorByUserId(String userId);
}