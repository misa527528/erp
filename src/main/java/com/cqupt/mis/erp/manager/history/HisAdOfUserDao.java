package com.cqupt.mis.erp.manager.history;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisAdOfUserDao")
public interface HisAdOfUserDao {
    // TODO: 2016/8/25 如果找不到返回0用null返回用nullif可不可以（因为使用了output）
    double getUserInput(@Param("userunique") String userunique,
                        @Param("period") int period);

    double getAdCost(@Param("userunique") String userunique,
                     @Param("period") int period);
}
