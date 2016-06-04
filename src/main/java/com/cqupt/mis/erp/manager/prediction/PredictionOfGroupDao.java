package com.cqupt.mis.erp.manager.prediction;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.prediction.PredictionOfGroup;
import com.cqupt.mis.erp.model.vo.PredictionOfGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("predictionOfGroupDao")
public interface PredictionOfGroupDao extends BaseDao {

    public void addPredictionOfGroup(PredictionOfGroup predictionOfGroup);

    public List<PredictionOfGroup> findAllPredictionOfGroup(String groupName);

    public boolean addPredictionOfGroups(List<PredictionOfGroup> predictionOfGroups);

    public List<PredictionOfGroupVO> findPredictionOfGroupByMarketName(String marketName, String groupName);

}
