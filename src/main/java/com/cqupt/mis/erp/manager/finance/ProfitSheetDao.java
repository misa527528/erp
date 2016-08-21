package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.ProfitSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("profitSheetDao")
public interface ProfitSheetDao {
    /**
     * 查询利润表
     * @param userUnique
     * @param period
     * @return
     */
    List<ProfitSheet> findProfitSheet(@Param("userUnique") String userUnique,
                                      @Param("period") int period);


}
