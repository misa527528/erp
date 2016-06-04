package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;

import java.util.List;

/**
 * Created by yangqing on 2016/6/4.
 */
public interface GameGroupMemberDao {
    /**
     *
     * findAllGameGroupList 查找所有的游戏组信息.
     * @return
     *List<GameGroupInfo>
     * @exception
     * @since  1.0.0
     * @author hhy
     */
    public List<GameGroupInfo> findAllGameGroupList();
}
