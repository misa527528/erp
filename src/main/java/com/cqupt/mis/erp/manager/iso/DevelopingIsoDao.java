package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.model.iso.ISODeveloping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developingIsoDao")
public interface DevelopingIsoDao {
    /**
     * 查看所有开拓中的iso
     * @param userUnique
     * @return
     */
    List<ISODeveloping> findAllISODeveloping(String userUnique);

    /**
     * 查看单个开拓中的iso
     * @param userUnique
     * @param isoName
     * @return
     */
    ISODeveloping findOneISODeveloping(@Param("userUnique") String userUnique,
                                       @Param("isoName") String isoName);

    /**
     * 更新ISO完成周期时间
     * @param userUnique
     * @param isoName
     * @return
     */
    int updateFinishPeriod(@Param("userUnique") String userUnique,
                           @Param("isoName") String isoName);

    /**
     * 删除一条DevelopingISO记录
     * @param userUnique
     * @param isoName
     * @return
     */
    int deleteDevelopingISO(@Param("userUnique") String userUnique,
                            @Param("isoName") String isoName);

    /**
     * 增加一个iso认证
     * @param userUnique
     * @param isoName
     * @param researchPeriod
     * @param researchCost
     * @param finishedPeriod
     * @param beginTime
     * @param status
     * @return
     */
    int addIsoToISODeveloping(@Param("userUnique") String userUnique,
                              @Param("isoName") String isoName,
                              @Param("researchPeriod") int researchPeriod,
                              @Param("researchCost") double researchCost,
                              @Param("finishedPeriod") int finishedPeriod,
                              @Param("beginTime") int beginTime,
                              @Param("status") int status);

    /**
     * 改变开发中认证的状态值
     * @param userUnique
     * @param isoName
     * @param status
     * @return
     */
    int updateISODevelopingStatus(@Param("userUnique") String userUnique,
                                  @Param("isoName") String isoName,
                                  @Param("status") int status);
}
