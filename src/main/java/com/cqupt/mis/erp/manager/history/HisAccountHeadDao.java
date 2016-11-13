package com.cqupt.mis.erp.manager.history;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisAccountHeadDao")
public interface HisAccountHeadDao {
    double getItemCost(@Param("userunique") String userunique,
                       @Param("item") String item,
                       @Param("firstPeriod") int firstPeriod,
                       @Param("lastPeriod") int lastPeriod);


}
