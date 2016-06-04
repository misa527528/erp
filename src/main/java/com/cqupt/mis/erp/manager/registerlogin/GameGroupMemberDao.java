package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;

import java.util.List;

/**
 * Created by yangqing on 2016/6/4.
 */
public interface GameGroupMemberDao {
    /**
     * findGameGroupMemberList 根据组名来找到所有组员的信息. 无序版
     *
     * @param groupName
     * @return List<GameGroupMemberInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<GameGroupMemberInfo> findGameGroupMemberList(String groupName);

    /**
     * findGroupNameByUserId 根据userID找到组名
     *
     * @param userID
     * @return 只返回一个组名 如果找不到返回空.
     * String
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public String findGroupNameByUserId(String userID);

    /**
     * updateGameGroupMember 作更新操作
     *
     * @param gameGroupMember
     * @return
     * @author lx
     */
    public boolean updateGameGroupMember(GameGroupMemberInfo gameGroupMember);

    /**
     * findUserUniqueByUserId 根据userId来找到userunique
     *
     * @param userId
     * @return String
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public String findUserUniqueByUserId(String userId);

    /**
     * findCurrentPeriod 根据userUnique来找到CurrentPeriod
     * 已经舍弃的方法. 主要使用commonDao 中的公告方法.
     *
     * @param userUnique
     * @return int
     * @throws
     * @author hhy
     * @since 1.0.0
     * @deprecated
     */
    public int findCurrentPeriod(String userUnique);

    /**
     * findGameGroupMemberListByStatus 根据status状态来找到哪个组的人
     *
     * @param status     状态值  用户状态—0表示用户已经破产，1表示用户仍然在经营.2表示完成竞赛。一开始的时候应该是null
     * @param userUnique 唯一用户标识
     * @return List<GameGroupMemberInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<GameGroupMemberInfo> findGameGroupMemberListByStatus(Integer status, String userUnique);

    /**
     * findGameGroupMemberListByStatusAndGroupName  根据status状态来找到哪个组的人
     *
     * @param status    状态值  用户状态  0表示用户已经破产  1表示用户仍然在经营   2表示完成竞赛。一开始的时候应该是null
     * @param groupName 组名
     * @return List<GameGroupMemberInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<GameGroupMemberInfo> findGameGroupMemberListByStatusAndGroupName(Integer status, String groupName);


    /**
     * 取出当前用户的状态值
     *
     * @param userUnique
     * @return
     * @author lx
     */
    public int findStatusByUserUnique(String userUnique);

    /**
     * 用户破产，更改用户status值为0
     *
     * @author lx
     */
    public boolean updateBankruptcyUserStatus(String userUnique, int status);

    /**
     * 将该用户推进下一周  CurrentPeriod + 1
     *
     * @param userUnique
     * @return
     * @author lx
     */
    public boolean updateIncreaseUserCurrentPeriod(String userUnique);

    /**
     * 找到当前游戏组里的最小期
     *
     * @param groupName
     * @return
     */
    public int findLeastCurrentPeriodByGroupName(String groupName);

    /**
     * 用户已经完成游戏，设置他的状态值为2
     *
     * @param userUnique
     * @return
     */
    public boolean updateUserStatusToFinishGame(String userUnique);

    /**
     * updateGameGroupNumber 更新用户组中的人数 减少一个单位
     *
     * @param groupName void
     * @throws
     * @since 1.0.0
     */
    public void updateGameGroupNumber(String groupName);

    public int delete(String groupName, String userUnique);

    public int updateUserNumbers(String groupName);

    public int findUserNumByGroupName(String groupName);

    public String findGroupNameByUserUnique(String userUnique);

    public List<GameGroupMemberInfo> findUsersByGroupName(String groupName);

}
