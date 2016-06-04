package com.cqupt.mis.erp.manager.finance.account;

import com.cqupt.mis.erp.model.finance.AccountDetail;

import java.util.List;

public interface AccountDetailDao {

    /**
     * 向向分录明细表(ACCOUNTDETAIL)表中添加一条记录
     *
     * @param userUnique 用户在竞赛中的唯一标识
     * @param item       科目的名称,外码，参照 ACCOUNTTWOTOONEBASIC表的TwoItem
     * @param itemType   科目对应的借或贷类型，其值只能是“借”或者“贷”
     * @param money      金额
     * @param calValue   CalValue是为了方便计算“资产负债表”和“利润”表而设立的值，其数值大小与Money字段相同，但是带有符号。
     * @author 毛家杰
     */
    public void addAccountDetail(String userUnique, Integer accountID, String item,
                                 String itemType, Double money, Double calValue);

    /**
     * 查询会计明细
     *
     * @param userUnique 用户唯一码
     * @param minID      最小的accountID
     * @param maxID      最大的accountID
     * @return
     * @author 毛家杰
     */
    public List<AccountDetail> findAccountDetail(String userUnique, Integer minID, Integer maxID);

    /**
     * 导出一坨翔,一言难尽
     *
     * @author 毛家杰
     * @see author's QQ:243429123
     * @param UserUnique 用户唯一码
     * @param minID 最小的accountID
     * @param maxID 最大的accountID
     * @return
     */
//	public Map<String,Object> findAccountIDAndCount(String userUnique,Integer minID,Integer maxID);

}
