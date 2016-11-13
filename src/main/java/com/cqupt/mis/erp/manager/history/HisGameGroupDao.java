package com.cqupt.mis.erp.manager.history;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisGameGroupDao")
public interface HisGameGroupDao {
    List<GameGroupInfo> getGroupNames();

    /**
     * 查看历史记录表中的每年周期数信息
     * @param userUnique
     * @return
     */
    Integer findHistoryPeriodOfYear(String userUnique);

    /**
     * 从his_gamegroup中找到相应的游戏组信息
     * @param groupName
     * @return
     */
    GameGroupInfo findHistoryGameGroupInfo(String groupName);
}
