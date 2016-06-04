package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.iso.ISODeveloped;
import com.cqupt.mis.erp.model.iso.ISODeveloping;
import com.cqupt.mis.erp.model.iso.ISOUndevelop;

import java.util.List;

/**
 * ISOManageDao iso研发控制的dao
 *
 * @version 1.0.0
 */
public interface ISOManageDao extends BaseDao {
    /**
     * 查看所有未开拓的iso
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public List<ISOUndevelop> findAllISOUndevelop(String userUnique);

    /**
     * 删除一条未开拓记录
     *
     * @param userUnique
     * @param isoName
     * @return
     * @author zy
     */
    public boolean delelteISOUndevelop(String userUnique, String isoName);

    /**
     * 查看所有开拓中的iso
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public List<ISODeveloping> findAllISODeveloping(String userUnique);

    /**
     * 查看单个开拓中的iso
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public ISODeveloping findOneISODeveloping(String userUnique, String isoName);


    /**
     * 更新ISO完成周期时间
     *
     * @param userUnique
     * @param isoName
     * @author zy
     */
    public boolean updateFinishPeriod(String userUnique, String isoName);

    /**
     * 删除一条DevelopingISO记录
     *
     * @param userUnique
     * @param isoName
     * @author zy
     */
    public boolean deleteDevelopingISO(String userUnique, String isoName);

    /**
     * 查看单个未开拓的iso
     *
     * @param userUnique
     * @param isoName
     * @return
     * @author zy
     */
    public ISOUndevelop findOneUndevelop(String userUnique, String isoName);

    /**
     * 增加一个iso认证
     *
     * @param iso
     * @return
     * @author zy
     */
    public boolean addIsoToISODeveloping(ISODeveloping iso);

    /**
     * 改变开发中认证的状态值
     *
     * @param userUnique
     * @param isoName
     * @param status
     * @return
     * @author zy
     */
    public boolean updateISODevelopingStatus(String userUnique, String isoName, int status);

    /**
     * 查看所有已开发的iso认证
     *
     * @param userUnique
     * @return
     * @author zy
     */
    public List<ISODeveloped> findAllISODeveloped(String userUnique);

    /**
     * 改变已开发认证的状态值
     *
     * @param userUnique
     * @param isoName
     * @param status
     * @return
     * @author zy
     */
    public boolean updateISODevelopedStatus(String userUnique, String isoName, int status);


    /**
     * 改变已开发认证的lastStatus
     *
     * @param userUnique
     * @param isoName
     * @param lastStatus
     * @return
     * @author zy
     */
    public boolean updateISODevelopedLastStatus(String userUnique, String isoName, int lastStatus);

    /**
     * 向已开拓市场中增加一条记录
     *
     * @param iso
     * @return
     */
    public boolean addISOToISODeveloped(ISODeveloped iso);

    /**
     * isISODeveloped  判断iso是否已经开发完成
     *
     * @param userUnique
     * @param ISOName
     * @return Boolean
     * @throws
     * @since 1.0.0
     */
    public Boolean isISODeveloped(String userUnique, String ISOName);

}
