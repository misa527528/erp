package com.cqupt.mis.erp.manager.iso;

import com.cqupt.mis.erp.model.iso.ISOUndevelop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("undevelopIsoDao")
public interface UndevelopIsoDao {
    /**
     * 查看所有未开拓的iso
     * @param userUnique
     * @return
     */
    List<ISOUndevelop> findAllISOUndevelop(String userUnique);

    /**
     * 删除一条未开拓记录
     * @param userUnique
     * @param isoName
     * @return
     */
    int delelteISOUndevelop(@Param("userUnique") String userUnique,
                            @Param("isoName")String isoName);

    /**
     * 查看单个未开拓的iso
     * @param userUnique
     * @param isoName
     * @return
     */
    ISOUndevelop findOneUndevelop(@Param("userUnique")String userUnique,
                                  @Param("isoName")String isoName);
}
