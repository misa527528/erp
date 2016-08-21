package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.model.iso.ISODeveloped;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developedIsoDao")
public interface DevelopedIsoDao {
    /**
     * 查看所有已开发的iso认证
     * @param userUnique
     * @return
     */
    List<ISODeveloped> findAllISODeveloped(String userUnique);

    /**
     * 改变已开发认证的状态值
     * @param userUnique
     * @param isoName
     * @param status
     * @return
     */
    int updateISODevelopedStatus(@Param("userUnique") String userUnique,
                                 @Param("ISOName") String isoName,
                                 @Param("status") int status);

    /**
     * 改变已开发认证的lastStatus
     * @param userUnique
     * @param isoName
     * @param lastStatus
     * @return
     */
    int updateISODevelopedLastStatus(@Param("userUnique") String userUnique,
                                     @Param("ISOName") String isoName,
                                     @Param("lastStatus") int lastStatus);

    /**
     * 向已开拓市场中增加一条记录
     * @param userUnique
     * @param isoName
     * @param status 状态:1 正在维护，0 暂停维护
     * @param beginTime 开发开始时间
     * @param endTime 开发完成时间
     * @param lastStatus 状态 ：上一周期是否支付了维持费，1 支付，0 未支付
     * @param maintainCost 每周期维护费用
     * @return
     */
    int addISOToISODeveloped(@Param("userUnique") String userUnique,
                             @Param("isoName") String isoName,
                             @Param("status") int status,
                             @Param("beginTime") int beginTime,
                             @Param("endTime") int endTime,
                             @Param("lastStatus") int lastStatus,
                             @Param("maintainCost") Double maintainCost);

    /**
     * 通过userUnique、ISOName来查找Developed ISO
     * @param userUnique
     * @param ISOName
     * @return
     */
    ISODeveloped findOneDevelopedISO(@Param("userUnique") String userUnique,
                                     @Param("ISOName") String ISOName);
}
