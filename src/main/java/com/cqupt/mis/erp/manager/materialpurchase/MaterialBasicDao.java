package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialBasic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("materialBasicDao")
public interface MaterialBasicDao {
    /**
     * 查询特定原材料的单价
     * @param materialName
     * @return
     */
    float findMateriaPrice(String materialName);

    /**
     * 收集额外数据 endtime 为购买原材料收集数据
     * @param materialName
     * @return
     */
    int findDelayTime(String materialName);

    List<MaterialBasic> findAllMaterialBasic();
}
