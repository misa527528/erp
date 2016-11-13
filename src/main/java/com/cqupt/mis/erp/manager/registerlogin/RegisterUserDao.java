package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("registerUserDao")
public interface RegisterUserDao {
    RegisterInfo findRegisterByUserId(String userId);

    int updateRegister(@Param("userID") String userID,
                       @Param("name") String name,
                       @Param("password") String password,
                       @Param("major") String major,
                       @Param("className") String className,
                       @Param("studentID") String studentID,
                       @Param("email") String email,
                       @Param("tel") String tel);

    List<RegisterInfo> findAllRegister();

    int deleteRegisterById(String registerId);

    int addRegister(@Param("userID") String userId,
                    @Param("userName") String userName,
                    @Param("password") String password,
                    @Param("major") String major,
                    @Param("className") String className,
                    @Param("studentID") String studentID,
                    @Param("email") String email,
                    @Param("tel") String tel,
                    @Param("status") String status);
}
