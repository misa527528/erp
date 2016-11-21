package com.cqupt.mis.erp.service.product.impl;

import com.cqupt.mis.erp.manager.materialpurchase.MaterialBasicDao;
import com.cqupt.mis.erp.manager.product.ProductBasicDao;
import com.cqupt.mis.erp.manager.product.ProductDetailBasicDao;
import com.cqupt.mis.erp.manager.product.ProductOfUserDao;
import com.cqupt.mis.erp.model.product.ProductDetailBasic;
import com.cqupt.mis.erp.model.product.ProductOfUser;
import com.cqupt.mis.erp.service.product.ProductBasicService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productBasicService")
public class ProductBasicServiceImpl implements ProductBasicService {
    @Resource
    private ProductBasicDao productBasicDao;
    @Resource
    private ProductOfUserDao productOfUserDao;
    @Resource
    private ProductDetailBasicDao productDetailBasicDao;
    @Resource
    private MaterialBasicDao materialBasicDao;

    public List<ProductOfUser> findProductInventories(String userUnique) {
        return productOfUserDao.findProductInventories(userUnique);
    }

    @Override
    public List<ProductDetailBasic> findProductDetail() {
        return productDetailBasicDao.findProductDetail();
    }

    @Override
    public float findProductPrice(String productName) {
        float A = 0;
        List<String> materialNames = productDetailBasicDao.findMaterialNamesBYProduct(productName);
        for (String Name : materialNames) {
            int needNumber = productDetailBasicDao.findMaterialNumber(productName, Name);
            float price = materialBasicDao.findMateriaPrice(Name);
            //lx:四舍五入处理
            A += ERPUtils.round(price * needNumber);    //会计分录里面的A值

        }
        return A;
    }

    @Override
    public boolean updateDecreasePNumber(ProductOfUser productOfUser) {
        String userUnique = productOfUser.getUserUnique();
        String productName = productOfUser.getProductName();
        int pNumber = productOfUser.getpNumber();

        int result = productOfUserDao.updateDecreasePNumber(userUnique, productName, pNumber);

        return result > 0;
    }

    @Override
    public List<String> findProductName() {
        return productBasicDao.findAllProductName();
    }


}
