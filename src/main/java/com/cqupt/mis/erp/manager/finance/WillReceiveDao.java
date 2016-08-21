package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.WillReceive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("willReceiveDao")
public interface WillReceiveDao {
    /**
     * 查询应收账款
     * @param userUnique
     * @return
     */
    List<WillReceive> getWillReceive(String userUnique);

    /**
     * 查询转化金额多少
     * @param userUnique
     * @param willReceiveID 应收账款码
     * @return
     */
    Double getMoney(@Param("userUnique") String userUnique,
                    @Param("willReceiveID") Integer willReceiveID);

    /**
     * 删除一条应收账款记录
     * @param userUnique
     * @param willReceiveID  应收账款码
     * @return
     */
    int delete(@Param("userUnique") String userUnique,
               @Param("willReceiveID") Integer willReceiveID);

    /**
     * 插入一条记录
     * @param userUnique
     * @param money
     * @param beginTime
     * @param endTime
     * @param willReceiveDescription
     * @param note
     * @return
     */
    int insert(@Param("userUnique") String userUnique,
               @Param("money")  Double money,
               @Param("beginTime")  Integer beginTime,
               @Param("endTime") Integer endTime,
               @Param("willReceiveDescription") String willReceiveDescription,
               @Param("note") String note);
}
