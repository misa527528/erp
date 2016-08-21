package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.model.iso.ISOBasic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("isoBasicDao")
public interface IsoBasicDao {
    /**
     * 取出所有ISO基础信息
     * @return
     */
    List<ISOBasic> findAllISOBasic();

    /**
     * 取出ISO基础信息条数
     * @return
     */
    Integer findISOBasicNum();

    ISOBasic findOneISOBasic(String isoName);
}
