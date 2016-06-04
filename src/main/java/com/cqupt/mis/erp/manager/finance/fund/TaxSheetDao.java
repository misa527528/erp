package com.cqupt.mis.erp.manager.finance.fund;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.finance.TaxSheet;

public interface TaxSheetDao extends BaseDao {

    /**
     * 更新税金
     *
     * @param taxSheet
     * @return
     * @author LX
     */
    public boolean updateTaxSheet(TaxSheet taxSheet);

    /**
     * 展现用户的税金
     *
     * @param userUnique
     * @return
     * @author LX
     */
    public TaxSheet findTaxSheetByUserUnique(String userUnique);
}
