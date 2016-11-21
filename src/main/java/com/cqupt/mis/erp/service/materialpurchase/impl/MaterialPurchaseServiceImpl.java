package com.cqupt.mis.erp.service.materialpurchase.impl;

import com.cqupt.mis.erp.manager.materialpurchase.BuyingMaterialDao;
import com.cqupt.mis.erp.manager.materialpurchase.MaterialBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialPurchaseRecord;
import com.cqupt.mis.erp.service.materialpurchase.MaterialPurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("materialPurchaseService")
public class MaterialPurchaseServiceImpl implements MaterialPurchaseService {
    @Resource
    private MaterialBasicDao materialBasicDao;
    @Resource
    private BuyingMaterialDao buyingMaterialDao;
    @Resource
    private GameGroupMemberDao gameGroupMemberDao;

    public boolean addMaterialPurchaseRecord(MaterialPurchaseRecord materialPurchaseRecord) {
        int result;
        String userUnique = materialPurchaseRecord.getUserUnique();
        String materialName = materialPurchaseRecord.getMaterialName();//原材料名称
        int materialNumber = materialPurchaseRecord.getMaterialNumber();//原材料数量
        String wareHouseName = materialPurchaseRecord.getWareHouseName();//存放仓库名称

        try {
            int happendTime = gameGroupMemberDao.findCurrentTime(userUnique);
            int endTime = materialBasicDao.findDelayTime(materialName) + happendTime;

            result = buyingMaterialDao.addMaterialPurchaseRecord(userUnique, materialName, materialNumber,
                    wareHouseName, happendTime, endTime);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return result > 0;
    }

    @Override
    public List<MaterialPurchaseRecord> findMaterialOrders(String userUnique,
                                                           int pageSize, int status, int pageNumber) {
        List<MaterialPurchaseRecord> orders = new ArrayList<MaterialPurchaseRecord>();
        int currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
        if (status == 0) {
            orders = buyingMaterialDao.findMaterialOrdersReach(userUnique, pageSize, pageNumber, currentTime);
        } else if (status == 1) {
            orders = buyingMaterialDao.findMaterialOrdersUnReach(userUnique, pageSize, pageNumber, currentTime);
        } else if (status == 2) {
            orders = buyingMaterialDao.findAllMaterialOrders(userUnique, pageSize, pageNumber);
        }
        return orders;
    }

    @Override
    public int findMaterialOrdersReachCount(String userUnique, int currentTime) {
        return buyingMaterialDao.findMaterialOrdersReachCount(userUnique, currentTime);
    }

    @Override
    public int findMaterialOrdersUnReachCount(String userUnique, int currentTime) {

        return buyingMaterialDao.findMaterialOrdersReachUnCount(userUnique, currentTime);
    }
}
