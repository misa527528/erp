package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.ProductLineBasic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("productLineBasicDao")
public interface ProductLineBasicDao {
    /**
     *  查询所有生产线基本情况
     * @return
     */
   // @Cacheable(value={"ProductLineBasic"})
    List<ProductLineBasic> findAllProductLineBasics();

    /**
     * 查看生产线转产需要的周期数
     * @param productLineType
     * @return
     */
   // @Cacheable(value={"ProductLineChangePeriod"},key="#productLineType")
    int findProductLineChangePeriod(String productLineType);

    /**
     * 获得收款延迟时间
     * @param productLineType
     * @return
     */
    int findDelayTime(String productLineType);

    /**
     * 查询出某类生产线的基本属性
     * @param productLineId
     * @return
     */
   // @Cacheable(value={"ProductLineBasicByProductLineId"},key="#productLineId")
    ProductLineBasic findProductLineBasic(String productLineId);

    /**
     * 这里是原来productLineDao.findABCList里面的find_C
     * @param productlineType
     * @return
     */
    float findC(String productlineType);
}
