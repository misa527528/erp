package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;

import java.util.List;

/**
 * Created by yangqing on 2016/6/4.
 */
public interface RegisterDao {
    public void updateRegister(RegisterInfo registerInfo);

    public void updateApprovedUser(RegisterInfo registerInfo);

    /**
     * 取出用户信息byUserId
     *
     * @param registerId
     * @return
     */
    public RegisterInfo findByUserId(String registerId);

    public List<RegisterInfo> findAllRegister();

    public boolean deleteRegisterById(String registerId);
}

