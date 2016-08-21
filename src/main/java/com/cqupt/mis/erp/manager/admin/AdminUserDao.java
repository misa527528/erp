package com.cqupt.mis.erp.manager.admin;

import com.cqupt.mis.erp.model.admin.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("adminUserDao")
public interface AdminUserDao {
    AdminUser findAdminUsersByAdminId(@Param("adminId") String adminId);

    /** 更新管理员密码
     * @param adminId
     * @param oldPWD
     * @param newPWD
     * @return
     */
    int updatePWD(@Param("adminId") String adminId,
                  @Param("oldPWD") String oldPWD,
                  @Param("newPWD") String newPWD);
}
