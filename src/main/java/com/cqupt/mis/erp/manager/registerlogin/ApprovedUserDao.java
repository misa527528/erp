package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("approvedUserDao")
public interface ApprovedUserDao {
    /**
     * 取出所有已经通过审批的用户列表
     * @return
     */
    // TODO 这方法貌似没有被引用过，项目接近完成时检查一下
    List<ApprovedUserInfo> findAllApprovedUserList();


    ApprovedUserInfo findApprovedUserByUserId(String userId);


    int addRegister(@Param("userID") String userID,
                    @Param("name") String name,
                    @Param("password") String password,
                    @Param("major") String major,
                    @Param("className") String className,
                    @Param("studentID") String studentID,
                    @Param("email") String email,
                    @Param("tel") String tel,
                    @Param("typeId") String typeId);

    int deleteApprovedUserById(@Param("approvedUserId") String approvedUserId);

    ApprovedUserInfo findGameGroupCreatorUserByGroupName(String groupName);

    List<ApprovedUserInfo> findAllGameGroupUserByGroupName(String groupName);

    int updateApprovedUserInfo(@Param("userID") String userID,
                               @Param("password") String password,
                               @Param("major") String major,
                               @Param("className") String className,
                               @Param("studentID") String studentID,
                               @Param("email") String email,
                               @Param("tel") String tel);

}
