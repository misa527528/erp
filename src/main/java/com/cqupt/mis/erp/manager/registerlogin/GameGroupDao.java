package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberStatus;
import com.cqupt.mis.erp.model.vo.GameGroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("gameGroupDao")
public interface GameGroupDao {
    /**
     * 查找所有的游戏组信息
     * @return
     */
    List<GameGroupInfo> findAllGameGroupList();

    /**
     * 根据游戏组名找到相应的游戏组信息
     * @param groupName
     * @return
     */
    GameGroupInfo findGameGroupInfoByGroupName(String groupName);

    /**
     * 取出游戏年
     * @param groupName
     * @return
     */
    int findYearsByGroupName(String groupName);

    /**
     * 根据userUnique来查找游戏组信息
     * @param userUnique
     * @return
     */
    GameGroupInfo findGameGroupInfoByUserUnique(String userUnique);

    /**
     * 根据UserUnique取出用户当前所在组的每年有多少期和当前期数(用于判断是否是年初)
     * @param userUnique
     * @return
     */
    GameGroupInfo findGameGroupYearAndCurrentPeriod(String userUnique);

    /**
     * 取出当前游戏组的期数
     * @param groupName
     * @return
     */
    int findCurrentPeriodByGroupName(String groupName);

    /**
     * 更新当前游戏组的期数
     * @param groupName
     * @param currentPeriod
     * @return
     */
    int updateGroupCurrentPeriod(String groupName,
                                 int currentPeriod);

    // TODO: 2016/8/18 重构
    GameGroupVO findGameGroupVO(Map map);

    /**
     * 暂时就放在这里吧
     * @return
     */
    List<GameGroupInfo> findAllGameGroups();

    /**
     * 也放在这里吧，毕竟status也是GameGroup表的字段
     * @param groupName
     * @return
     */
    // TODO: 2016/8/18 该方法的sql写的有点复杂，看看能不能重构
    List<GameGroupMemberStatus> findGameGroupMemberStatusByGroupName(String groupName);

    /**
     * 将游戏组的信息转入到历史数据
     * @param groupName
     * @result 0
     * @return
     */
    // TODO: 2016/8/18 xml里面写的东西不太懂，要搞明白，看看是否要搬倒别的地方
    int addHistory(@Param("groupName") String groupName,
                   @Param("result") int result);

    /**
     * 也放在这里吧，毕竟status也是GameGroup表的字段
     * @param groupName
     * @return
     */
    // TODO: 2016/8/18 xml里面写的东西不太懂，也要看看是否要搬倒别的地方
    List<GameGroupMemberStatus> findGameGroupMemberStatusbygroupNameWithoutStartGame(String groupName);

    /**
     * 更新用户组中的人数 减少一个单位
     * @param groupName
     * @return
     */
    int updateGameGroupNumber(String groupName);

    int updateUserNumbers(String groupName);

    // TODO: 2016/8/18 这好像错了吧，sql写的意思和方法名称完全对不上
    int findGameCreatorByUserId(String userId);

}
