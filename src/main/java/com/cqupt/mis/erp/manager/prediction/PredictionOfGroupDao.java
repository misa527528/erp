package com.cqupt.mis.erp.manager.prediction;

import com.cqupt.mis.erp.model.prediction.PredictionOfGroup;
import com.cqupt.mis.erp.model.vo.PredictionOfGroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("predictionOfGroupDao")
public interface PredictionOfGroupDao {
    /**
     * 此单个提交方法已被下面的批量提交方法addPredictionOfGroups()替代.2014.10.21
     *（此处提交了N次，必须修改为批量提交！必须！2014.10.15）
     * @param groupName
     * @param period
     * @param marketName
     * @param productName
     * @param price
     * @param mount
     * @return
     */
    int addPredictionOfGroup(@Param("groupName") String groupName,
                             @Param("period") Integer period,
                             @Param("marketName") String marketName,
                             @Param("productName") String productName,
                             @Param("price") Double price,
                             @Param("mount") Integer mount);

    /**
     * 批量添加某游戏组的预测信息到数据库
     * @param predictionOfGroups
     * @return
     */
    // TODO: 2016/8/23 想办法重构
    int addPredictionOfGroups(List<PredictionOfGroup> predictionOfGroups);

    /**
     * 取出某游戏组所有的预测信息
     * @param groupName
     * @return
     */
    List<PredictionOfGroup> findAllPredictionOfGroup(String groupName);

    /**
     * 根据不同市场，取出相应的预测信息
     * @param marketName
     * @param groupName
     * @return
     */
   // @Cacheable(value={"PredictionOfGroup"},key="#marketName+#groupName")
    List<PredictionOfGroupVO> findPredictionOfGroupByMarketName(@Param("marketName") String marketName,
                                                                @Param("groupName") String groupName);
}
