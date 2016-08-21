package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.TaxSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("taxSheetDao")
public interface TaxSheetDao {
    /**
     * 更新税金
     * @param userUnique
     * @param tax 税金
     * @return
     */
    int updateTaxSheet(@Param("userUnique") String userUnique,
                       @Param("tax") float tax);

    /**
     * 展现用户的税金
     * @param userUnique
     * @return
     */
    TaxSheet findTaxSheetByUserUnique(String userUnique);
}
