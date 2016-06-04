package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.springframework.stereotype.Repository;

@Repository("materialBasicDao")
public interface MaterialBasicDao extends BaseDao {
    /**
     * 查询特定原材料的单价
     *
     * @param materialName
     * @return
     * @author zy
     */
    public float findMateriaPrice(String materialName);

}
