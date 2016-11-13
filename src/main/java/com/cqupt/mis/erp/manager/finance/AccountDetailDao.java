package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.AccountDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("accountDetailDao")
public interface AccountDetailDao {
    /**
     * 向分录明细表(ACCOUNTDETAIL)表中添加一条记录
     * @param userUnique 用户在竞赛中的唯一标识
     * @param accountID
     * @param item 科目的名称,外码，参照 ACCOUNTTWOTOONEBASIC表的TwoItem
     * @param itemType 科目对应的借或贷类型，其值只能是“借”或者“贷”
     * @param money 金额
     * @param calValue CalValue是为了方便计算“资产负债表”和“利润”表而设立的值，其数值大小与Money字段相同，但是带有符号。
     * @return
     */
    int addAccountDetail(@Param("userUnique") String userUnique,
                         @Param("accountID") Integer accountID,
                         @Param("item") String item,
                         @Param("itemType")  String itemType,
                         @Param("money") Double money,
                         @Param("calValue") Double calValue);

    /**
     * 查询会计明细
     * @param userUnique 用户唯一码
     * @param minID 最小的accountID
     * @param maxID  最大的accountID
     * @return
     */
    List<AccountDetail> findAccountDetail(@Param("userUnique") String userUnique,
                                          @Param("minID") Integer minID,
                                          @Param("maxID") Integer maxID);

}
