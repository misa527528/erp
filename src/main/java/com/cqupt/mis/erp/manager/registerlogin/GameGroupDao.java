package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberStatus;
import com.cqupt.mis.erp.model.vo.GameGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangqing on 2016/6/4.
 */
@Repository("gameGroupDao")
public interface GameGroupDao {
    /**
     * findAllGameGroupList 查找所有的游戏组信息.
     *
     * @return List<GameGroupInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<GameGroupInfo> findAllGameGroupList();

    /**
     * findGameGroupInfoByGroupName 根据游戏组名 找到相应的游戏组信息
     *
     * @param groupName
     * @return GameGroupInfo
     * @throws
     * @since 1.0.0
     */
    public GameGroupInfo findGameGroupInfoByGroupName(String groupName);

    /**
     * 取出游戏年
     *
     * @param groupName
     * @return
     * @author lx
     */
    public int findYearsByGroupName(String groupName);

    /**
     * findGameGroupInfoByUserUnique  根据userUnique来查找游戏组信息
     *
     * @param userUnique
     * @return GameGroupInfo
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public GameGroupInfo findGameGroupInfoByUserUnique(String userUnique);

    /**
     * 根据UserUnique取出用户当前所在组的每年有多少期和当前期数(用于判断是否是年初)
     */
    public GameGroupInfo findGameGroupYearAndCurrentPeriod(String userUnique);

    /**
     * 取出当前游戏组的期数
     *
     * @param groupName
     * @return
     */
    public int findCurrentPeriodByGroupName(String groupName);

    /**
     * 更新当前游戏组的期数
     *
     * @author lx
     */
    public boolean updateGroupCurrentPeriod(String groupName, int currentPeriod);

    /**
     *
     */
    public GameGroupVO findGameGroupVO(Map map);

    /**
     * @author zjs
     */
    public List<GameGroupInfo> findAllGameGroups();

    public List<GameGroupMemberStatus> findGameGroupMemberStatusByGroupName(String groupName);

    /**
     * addHistory 将游戏组的信息转入到历史数据
     *
     * @param groupName void
     */
    public boolean addHistory(String groupName);


}
