package com.cqupt.mis.erp.service.factory.impl;

import com.cqupt.mis.erp.manager.factory.FactoryBasicDao;
import com.cqupt.mis.erp.manager.factory.ProductLineBasicDao;
import com.cqupt.mis.erp.manager.factory.ProductLineDao;
import com.cqupt.mis.erp.manager.materialpurchase.MaterialBasicDao;
import com.cqupt.mis.erp.manager.materialpurchase.MaterialOfUserDao;
import com.cqupt.mis.erp.manager.product.ProductDetailBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.factory.ProductLineBasic;
import com.cqupt.mis.erp.model.factory.ProductLineCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import com.cqupt.mis.erp.service.factory.ProductLineService;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.service.finance.fund.WillReceiveService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productLineService")
public class ProductLineServiceImpl implements ProductLineService {
    @Resource
    private FactoryBasicDao factoryBasicDao;
    @Resource
    private ProductLineDao productLineDao;
    @Resource
    private ProductDetailBasicDao productDetailBasicDao;
    @Resource
    private ProductLineBasicDao productLineBasicDao;
    @Resource
    private MaterialOfUserDao materialOfUserDao;
    @Resource
    private MaterialBasicDao materialBasicDao;
    @Resource
    private AccountHeadService accountHeadService;
    @Resource
    private AccountDetailService accountDetailService;
    @Resource
    private WillReceiveService willReceiveService;
    @Resource
    private GameGroupMemberDao gameGroupMemberDao;

    public List<ProductLineInfo> findProductLines(String userUnique,
                                                  String factoryId) {
        List<ProductLineInfo> productLineInfoList = new ArrayList<>();
        List<ProductLineInfo> productLineInfos = productLineDao.findProductLines(userUnique, factoryId);
        if (productLineInfos.size() > 0) {
            for (ProductLineInfo productlineinfo : productLineInfos) {
                int status = Integer.parseInt(productlineinfo.getStatus());
                if (status == 0)
                    productlineinfo.setStatus("安装中");
                if (status == 1)
                    productlineinfo.setStatus("暂定安装");
                if (status == 2)
                    productlineinfo.setStatus("生产中");
                if (status == 3)
                    productlineinfo.setStatus("暂停生产");
                if (status == 4)
                    productlineinfo.setStatus("待生产");
                if (status == 5)
                    productlineinfo.setStatus("转产中");
                if (status == 6)
                    productlineinfo.setStatus("暂停转产");
                productLineInfoList.add(productlineinfo);
            }
        } else {
// -----------------------可能没用----------------------------------------////
            ProductLineInfo p = new ProductLineInfo();
            p.setFactoryId(factoryId);
            p.setUserUnique(userUnique);
            productLineInfoList.add(p);
        }
        return productLineInfoList;

    }


    //这个方法荒废掉. 展示的部分就交给前台去做.
    @Override
    public ProductLineCommonInfo findProductLineDetail(String userUnique,
                                                       String productLineId) {
        ProductLineCommonInfo productLineCommonInfo = productLineDao
                .findProductLineDetail(userUnique, productLineId);
        int status = Integer.parseInt(productLineCommonInfo.getStatus());
        if (status == 0 || status == 5 || status == 1 || status == 6) {
            if (status == 0)
                productLineCommonInfo.setStatus("安装中");
            if (status == 1)
                productLineCommonInfo.setStatus("暂停安装");
            if (status == 5)
                productLineCommonInfo.setStatus("转产中");
            if (status == 6)
                productLineCommonInfo.setStatus("暂停转产");
            productLineCommonInfo.setRem("生产线已完成安装的周期数或者转产完成的周期数");
            productLineCommonInfo.setFinishPeriod(productLineCommonInfo
                    .getFinishPeriod());
        } else {
            if (status == 2 || status == 3) {
                if (status == 2)
                    productLineCommonInfo.setStatus("生产中");
                if (status == 3)
                    productLineCommonInfo.setStatus("暂停生产");
                productLineCommonInfo.setRem("在制产品已经完成的生产周期数");
                productLineCommonInfo
                        .setProductFinishPeriod(productLineCommonInfo
                                .getProductFinishPeriod());
            } else {
                productLineCommonInfo.setStatus("待生产");
            }
        }
        return productLineCommonInfo;
    }

    @Override
    public ProductLineCommonInfo findProductLineDetailExceptStatus(String userUnique, String productLineId) {
        return productLineDao.findProductLineDetail(userUnique, productLineId);
    }


    @Override
    public List<ProductLineBasic> findProductLineBasics() {
        return productLineBasicDao.findAllProductLineBasics();
    }

    @Override
    public boolean findIsAddProductLine(String userUnique, String factoryId) {
        int capacity = factoryBasicDao.findFactoryCapacity(userUnique, factoryId);
        int productLineNumber = productLineDao.findProductLineNumber(userUnique, factoryId);
        int number = capacity - productLineNumber;
        if (number < 0 || number == 0) return false;
        return true;
    }

    @Override
    public int addProductLine(String userUnique, String factoryId, ProductLineCommonInfo productlineinfo) {
        int result;
        if (!findIsAddProductLine(userUnique, factoryId)) { // 容量已满，不能新增生产线
            result = 2;
        } else {
            String productLineType = productlineinfo.getProductLineType();
            String productName = productlineinfo.getProductName();

            int addResult = productLineDao.addProductLine(userUnique, factoryId, productLineType, productName);

            if (addResult > 0) {
                result = 1;
            } else { // 添加失败
               result = 0;
            }
        }

        return result;
    }

    public int findProductLineStatus(String userUnique, String productLineId) {
        return productLineDao.findProductLineStatus(userUnique, productLineId);

    }

    @Override
    public boolean updateStartProduct(String userUnique, String productLineId) {
        boolean result = true;
        String productName;
        List<String> materialNames;
        int needNumber;
        int haveNumber;
        double A = 0;
        //得到生产线上的产品类型
        productName = productLineDao.findProductNameByProductLine(userUnique, productLineId);
        //得到产品所需原材料类型
        materialNames = productDetailBasicDao.findMaterialNamesBYProduct(productName);
        for (String materialName : materialNames) {
            //得到需要的原材料数量			
            needNumber = productDetailBasicDao.findMaterialNumber(productName, materialName);
            //得到该原材料库存数量			
            haveNumber = materialOfUserDao.findInventoryWithMaterialName(userUnique, materialName);
            if (needNumber > haveNumber) {
                System.out.println("库存不够");
                result = false;
                break;
            }
        }
        if (result) {
            //得到产品单位成本价	
            //needNumber=0;
            for (String Name : materialNames) {
                needNumber = productDetailBasicDao.findMaterialNumber(productName, Name);
                double price = materialBasicDao.findMateriaPrice(Name);
                A += price * needNumber;    //会计分录里面的A值			
                //更改库存
                A = ERPUtils.round(A);//lx:四舍五入的double处理
                materialOfUserDao.updateMaterialInventory(userUnique, Name, needNumber);
            }
            //更改生产线的status
            int update = productLineDao.updateProductLineStatus(userUnique, productLineId, 2);
            if (update >= 1) System.out.println("更改状态为2成功");
            else System.out.println("更改状态为2不成功");
            //删除库存量为零的记录
            //删除有必要么???你觉得捏? 
            //@author 何海源
            //2015.5.31 不用这个方法. 
            /*if(materialOfUserDao.findInventoryWithZero(userUnique)!=0){
				materialOfUserDao.delelteInventoryWithZero(userUnique);
			}*/
            //插入会计分录信息。
            int happenTime = gameGroupMemberDao.findCurrentTime(userUnique);
            accountHeadService.addAccountHead(userUnique, happenTime, "生产产品");
            accountDetailService.addAccountDetail(userUnique, "在制品", "原材料", "借", "贷", A, A, (-A));
        }
        return result;
    }

    @Override
    public boolean updateReStartProduct(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 2);

        return result > 0;
    }

    @Override
    public boolean updateStopProduct(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 3);

        return result > 0;
    }

    @Override
    public boolean updateRestall(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 0);

        return result > 0;
    }

    @Override
    public boolean updateStopstall(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 1);

        return result > 0;
    }

    @Override
    public boolean updateReChangeProduct(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 5);

        return result > 0;
    }

    @Override
    public boolean updateStopChangeProduct(String userUnique, String productLineId) {
        int result = productLineDao.updateProductLineStatus(userUnique, productLineId, 6);

        return result > 0;
    }

    @Override
    public boolean findTransferIsFinished(String userUnique, String productLineId) {
        String productLineType = productLineDao.findProductLineType(userUnique, productLineId);
        int changePeriod = productLineBasicDao.findProductLineChangePeriod(productLineType);
        int finishPeriod = productLineDao.findProductLineFinishPeriod(userUnique, productLineId);
        if (finishPeriod >= changePeriod) {
            return true;
        }
        return false;
    }

    @Override
    // TODO: 2016/8/22 重构
    public boolean updateStartChangeProduct(String userUnique, String productLineId,
                                            String productName) {
        boolean result = false;
        if (productLineDao.updateProductLineStatusTofive(userUnique, productLineId, productName) > 0) {
            result = true;
            if (findTransferIsFinished(userUnique, productLineId)) {//hhy:判断是否转产完成.
                if (productLineDao.updateProductLineStatus(userUnique, productLineId, 4) != 1) {
                    System.out.println("转为待生产状态不成功");
                } else {
                    System.out.println("直接转为待生产状态");
                }
            }
        } else {
            System.out.println("转产不成功");
        }

        return result;
    }

    @Override
    public void deleteSaleProductLine(String userUnique, String productLineId, String productLineType) {
        @SuppressWarnings("unchecked")
        Map<String, Object> accountMap = this.findABCList(userUnique, productLineId, productLineType);
        double A = (Double) accountMap.get("A");
        double B = (Double) accountMap.get("B");
        double C = (Double) accountMap.get("C");
        int happenTime = gameGroupMemberDao.findCurrentTime(userUnique);
        int endTime = productLineBasicDao.findDelayTime(productLineType) + happenTime;
        //插入第一条会计分录信息。
        accountHeadService.addAccountHead(userUnique, happenTime, "出售生产线_1");
        accountDetailService.addAccountDetail(userUnique, "固定资产清理", "累计折旧", "生产线", "借", "借", "贷", A, B, (-C));
        //插入第二条会计分录明细信息
        accountHeadService.addAccountHead(userUnique, happenTime, "出售生产线_2");
        accountDetailService.addAccountDetail(userUnique, "应收账款", "固定资产清理", "借", "贷", A, A, (-A));
        //处理应收账款表
        willReceiveService.addWillReceive(userUnique, A, happenTime, endTime, "出售生产线的应收账款", "");
        //删除factory_Using表中的信息。
        productLineDao.deleteProductLine(userUnique, productLineId);
    }


    @Override
    public ProductLineBasic findProductLineBasic(String productLineId) {
        return productLineBasicDao.findProductLineBasic(productLineId);
    }


    @Override
    public ProductLineInfo findProductLineInfo(String productLineId) {
        return productLineDao.findProductLineInfoByProductlineid(productLineId);
    }


    @Override
    public boolean withdrawProductLine(String userUnique, String productLineId) {
        int result = productLineDao.deleteProductLine(userUnique, productLineId);

        return result > 0;
    }

    // findABCList是原来productLineDaoImpl.findABCList方法
    private Map<String, Object> findABCList(String userUnique, String productLineId,String productlineType) {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        param.put("userUnique", userUnique);
        param.put("productLineId", productLineId);
        float a = productLineBasicDao.findC(productlineType);
        float c = productLineDao.findA(userUnique, productLineId);
        float b = c-a;
        double A = new Double(a);
        double B = new Double(b);
        double C = new Double(c);
        result.put("A", A);
        result.put("B", B);
        result.put("C", C);
        return result;
    }

}
					
