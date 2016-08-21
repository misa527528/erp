package com.cqupt.mis.erp.manager.product;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("productBasicDao")
public interface ProductBasicDao {
    /**
     * 查找所有的产品的名称
     * @return
     */
    List<String> findAllProductName();
}
