package com.cqupt.mis.erp.manager.finance.fund;

import com.cqupt.mis.erp.model.finance.WillReceive;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("willReceiveDao")
public interface WillReceiveDao {
    /**
     * 查询应收账款
     *
     * @param userUnique 用户唯一码
     * @return
     * @author 毛家杰
     */
    public List<WillReceive> getWillReceive(String userUnique);

    /**
     * 查询转化金额多少
     *
     * @param userUnique    用户唯一码
     * @param willReceiveID 应收账款码
     * @return
     * @author 毛家杰
     */
    public Double getMoney(String userUnique, Integer willReceiveID);

    /**
     * 删除一条应收账款记录
     *
     * @param userUnique    用户唯一码
     * @param willReceiveID 应收账款码
     * @author 毛家杰
     */
    public void delete(String userUnique, Integer willReceiveID);

    /**
     * 插入一条记录
     *
     * @param userUnique
     * @param money
     * @param beginTime
     * @param endTime
     * @param willReceiveDescription
     * @param note
     */
    public void insert(String userUnique, Double money,
                       Integer beginTime, Integer endTime, String willReceiveDescription,
                       String note);
}
