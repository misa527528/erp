package com.cqupt.mis.erp.manager.history;

import com.cqupt.mis.erp.model.enterpriseevaluate.ProduceCapacity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisProductLineDao")
public interface HisProductLineDao {
    // TODO: 2016/8/25 原来的sql命名是 his_getMemberCapacity，要不要改名
    List<ProduceCapacity> getProducePeriod(String userunique);
}
