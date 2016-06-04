package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialPurchaseRecord;

import java.util.List;

public interface MaterialPurchaseDao extends BaseDao {
    /**
     * 收集额外数据 endtime 为购买原材料收集数据
     *
     * @return MaterialPurchaseRecord
     * @author zy
     */

    public int findDelayTime(String materialName);

    /**
     * 购买原材料订单查询(已到货),添加了分页
     *
     * @return MaterialPurchaseRecord
     * @author zy
     */
    public List<MaterialPurchaseRecord> findMaterialOrdersReach(String userUnique, int pageSize, int pageNumber, int currentTime);

    /**
     * 查询已到货订单总数
     *
     * @param userUnique
     * @param currentTime
     * @return
     */
    public int findMaterialOrdersReachCount(String userUnique, int currentTime);

    /**
     * 购买原材料订单查询(未到货),添加了分页
     *
     * @return MaterialPurchaseRecord
     * @author zy
     */
    public List<MaterialPurchaseRecord> findMaterialOrdersUnReach(String userUnique, int pageSize, int pageNumber, int currentTime);


    /**
     * 查看用户所有订单
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public List<MaterialPurchaseRecord> findAllMaterialOrders(String userUnique, int pageSize, int pageNumber);

    /**
     * 查询未到货订单总数
     *
     * @param userUnique
     * @param currentTime
     * @return
     */
    public int findMaterialOrdersReachUnCount(String userUnique, int currentTime);
}
