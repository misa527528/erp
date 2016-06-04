package com.cqupt.mis.erp.manager.admin;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;

import java.util.List;

public interface ApprovedUserDao extends BaseDao {
    //取出所有已经通过审批的用户列表
    public List<ApprovedUserInfo> findAllApprovedUserList();

    //允许通过用户审批的方法
    public boolean passRegister(RegisterInfo registerInfo);

    //删除用户
    public boolean deleteApprovedUserById(String approvedUserId);

    /**
     * findGameGroupCreatorUserByGroupName 寻找创建者的详细信息.
     *
     * @param groupName 游戏组
     * @return ApprovedUserInfo
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public ApprovedUserInfo findGameGroupCreatorUserByGroupName(String groupName);

    /**
     * findAllGameGroupUserByGroupName 查找游戏组所有成员的详细信息.
     *
     * @param groupName
     * @return List<ApprovedUserInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<ApprovedUserInfo> findAllGameGroupUserByGroupName(String groupName);

    /**
     * 修改用户信息
     */
    public boolean updateApprovedUserInfo(ApprovedUserInfo approvedUserInfo);
}
