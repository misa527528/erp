package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("gameGroupMemberDao")
public interface GameGroupMemberDao {
    /**
     * 根据userID找到组名
     * @param userID
     * @return 只返回一个组名 如果找不到返回空.
     */
    String findGroupNameByUserId(String userID);

    /**
     * 作更新操作
     * @param userUnique
     * @param currentPeriod 成员当前正处于什么周期。初始值为0表示竞赛尚未开始
     * @param status 用户状态—0表示用户已经破产，1表示用户仍然在经营.2表示完成竞赛。
     *               10是还没有开始比赛，但是已经进入分组了的。一开始的时候应该是null
     * @param userID 参与该竞赛分组的用户ID
     * @param groupName
     * @return
     */
    int updateGameGroupMember(@Param("userUnique") String userUnique,
                              @Param("currentPeriod") int currentPeriod,
                              @Param("status") int status,
                              @Param("userID") String userID,
                              @Param("groupName") String groupName);

    /**
     * 根据userId来找到userunique
     * @param userId
     * @return
     */
    String findUserUniqueByUserId(String userId);

    /**
     * 根据userUnique来找到CurrentPeriod
     * 已经舍弃的方法. 主要使用commonDao中的公告方法.
     * @param userUnique
     * @return
     */
    // TODO: 2016/8/18 要去看一下commonDao中的公告方法
    int findCurrentPeriod(String userUnique);

    /**
     * 根据status状态来找到哪个组的人
     * @param status 状态值  用户状态—0表示用户已经破产，1表示用户仍然在经营.
     *               2表示完成竞赛。一开始的时候应该是null
     * @param userUnique
     * @return
     */
    List<GameGroupMemberInfo> findGameGroupMemberListByStatus(
            @Param("status") Integer status,
            @Param("userUnique") String userUnique);

    /**
     * 根据status状态来找到哪个组的人
     * @param status 状态值  用户状态  0表示用户已经破产
     *               1表示用户仍然在经营   2表示完成竞赛。
     *               一开始的时候应该是null
     * @param groupName  组名
     * @return
     */
    List<GameGroupMemberInfo> findGameGroupMemberListByStatusAndGroupName(
            @Param("status") Integer status,
            @Param("groupName") String groupName);

    /**
     * 取出当前用户的状态值
     * @param userUnique
     * @return
     */
    int findStatusByUserUnique(String userUnique);

    /**
     * 用户破产，更改用户status值为0
     * @param userUnique
     * @param status
     * @return
     */
    int updateBankruptcyUserStatus(@Param("userUnique") String userUnique,
                                   @Param("status") int status);

    /**
     * 将该用户推进下一周  CurrentPeriod + 1
     * @param userUnique
     * @return
     */
    int updateIncreaseUserCurrentPeriod(String userUnique);

    /**
     * 找到当前游戏组里的最小期
     * @param groupName
     * @return
     */
    int findLeastCurrentPeriodByGroupName(String groupName);

    /**
     * 用户已经完成游戏，设置他的状态值为2
     * @param userUnique
     * @return
     */
    int updateUserStatusToFinishGame(String userUnique);

    /**
     * 更新用户组中的人数 减少一个单位
     * @param groupName
     * @return
     */
    int updateGameGroupNumber(String groupName);

    int deleteByUserUnique(@Param("groupName") String groupName,
                           @Param("userUnique") String userUnique);

    int findUserNumByGroupName(String groupName);

    String findGroupNameByUserUnique(String userUnique);

    List<GameGroupMemberInfo> findUsersByGroupName(String groupName);

    GameGroupMemberInfo finGroupMemberByUserId(String userId);

    GameGroupMemberInfo findUserUniqueInGroupMemberByUserId(String userId);

    /**
     * 获取这个正在游戏的用户的相关情况
     * 0: 表示破产 1: 游戏正在进行 2: 用户已经完成游戏周期
     * @param userUnique
     * @return
     */
    int getStatusByUserUnique(String userUnique);



}
