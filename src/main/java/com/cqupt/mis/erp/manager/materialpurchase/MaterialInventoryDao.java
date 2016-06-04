package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("materialInventoryDao")
public interface MaterialInventoryDao extends BaseDao {
    /**
     * 原材料库存查询
     *
     * @return
     * @author zy
     */
    public List<MaterialInventory> findMaterialInventories(String userUnique);

    /**
     * 查询特定原材料库存
     *
     * @param materialName
     * @return
     */
    public int findInventoryWithMaterialName(String userUnique, String materialName);

    /**
     * 更新原料库存
     *
     * @param userUnique
     * @param materialName
     * @param mNumber
     * @return
     * @author zy
     */
    public boolean updateMaterialInventory(String userUnique, String materialName, int mNumber);

    /**
     * 查找库存为零的项的数量
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public int findInventoryWithZero(String userUnique);

    /**
     * 删除库存为0的项
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public boolean delelteInventoryWithZero(String userUnique);


}
