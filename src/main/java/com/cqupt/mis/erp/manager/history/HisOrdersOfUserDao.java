package com.cqupt.mis.erp.manager.history;

import com.cqupt.mis.erp.model.enterpriseevaluate.Products;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisOrdersOfUserDao")
public interface HisOrdersOfUserDao {
    double getUserOutput(@Param("userUnique") String userunique,
                         @Param("firstPeriod") int firstPeriod,
                         @Param("lastPeriod") int lastPeriod);

    double getMemberSaleByMarket(@Param("userunique") String userunique,
                                 @Param("marketname") String marketname,
                                 @Param("firstPeriod") int firstPeriod,
                                 @Param("lastPeriod") int lastPeriod);

    double getMemberSaleByProduct(@Param("userunique") String userunique,
                                  @Param("productName") String productName,
                                  @Param("firstPeriod") int firstPeriod,
                                  @Param("lastPeriod") int lastPeriod);

    List<Products> getProducts(@Param("userunique") String userunique,
                               @Param("firstPeriod") int firstPeriod,
                               @Param("lastPeriod") int lastPeriod);

    List<Products> getProductsByProductName(@Param("userUnique") String userUnique,
                                            @Param("productName") String productName,
                                            @Param("firstPeriod") int firstPeriod,
                                            @Param("lastPeriod") int lastPeriod);


}
