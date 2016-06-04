package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.iso.ISOBasic;

import java.util.List;

public interface ISOBasicDao extends BaseDao {

    /**
     * @return
     * @author LX
     * 取出所有ISO基础信息
     */
    public List<ISOBasic> findAllISOBasic();

    /**
     * @return
     * @author LX
     * 取出ISO基础信息条数
     */
    public Integer findISOBasicNum();

    public ISOBasic findOneISOBasic(String isoName);

}	
