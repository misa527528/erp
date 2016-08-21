package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("materialOfUserDao")
public interface MaterialOfUserDao {
    /**
     * 原材料库存查询
     * @param userUnique
     * @return
     */
    List<MaterialInventory> findMaterialInventories(String userUnique);

    /**
     * 查询特定原材料库存
     * @param userUnique
     * @param materialName
     * @return
     */
    int findInventoryWithMaterialName(@Param("userUnique") String userUnique,
                                      @Param("materialName") String materialName);

    /**
     * 更新原料库存
     * @param userUnique
     * @param materialName
     * @param mNumber
     * @return
     */
    int updateMaterialInventory(@Param("userUnique") String userUnique,
                                @Param("materialName") String materialName,
                                @Param("mNumber") int mNumber);

    /**
     * 查找库存为零的项的数量
     * @param userUnique
     * @return
     */
    int findInventoryWithZero(String userUnique);

    /** 删除库存为0的项
     * @param userUnique
     * @param mNumber 0
     * @return
     */
    int delelteInventoryWithZero(@Param("userUnique") String userUnique,
                                 @Param("mNumber") int mNumber);
}
