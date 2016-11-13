package com.cqupt.mis.erp.manager.registerlogin;

import com.cqupt.mis.erp.model.enterpriseevaluate.AdminIncomeBean;
import com.cqupt.mis.erp.model.enterpriseevaluate.Member;
import com.cqupt.mis.erp.model.enterpriseevaluate.UserInputAndOutputOfAd;
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
     * 查询当前进行到的周期时间
     * @param userUnique
     * @return
     */
    int findCurrentPeriod(String userUnique);

    /**
     * 根据status状态来找到哪个组的人
     * @param status 状态值  用户状态—0表示用户已经破产，1表示用户仍然在经营，2表示完成竞赛。一开始的时候应该是null
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
     * 根据组名来找到所有组员的信息. 无序版
     * @param groupName
     * @return
     */
    List<GameGroupMemberInfo> findGameGroupMemberList(String groupName);

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

    int addGameGroupMember(@Param("groupName") String groupName,
                           @Param("userId") String userId,
                           @Param("userUnique") String userUnique,
                           @Param("currentPeriod") int currentPeriod);

    // TODO: 2016/8/24 添加测试
    List<AdminIncomeBean> getUserUnique(@Param("groupname") String groupname,
                                        @Param("currentperiod")  int currentperiod);

    // TODO: 2016/8/24 添加测试，该接口原来为 enterPriseEvaluateDao的private接口：getGroupMembers
    List<UserInputAndOutputOfAd> getGroupMembersByGroupName(String groupName);

    List<UserInputAndOutputOfAd> getGroupMembers(String groupName);

    List<Member> getGroup_Members(String groupName);

    Member getGroup_Member(String userunique);

    /**
     * 查询当前进行到的周期时间
     * @param userUnique
     * @return
     */
    Integer findCurrentTime(String userUnique);

    /**
     * 根据userUnique 来查找相应的游戏组名称
     * @param userUnique
     * @return
     */
    //@Cacheable(value={"groupName"},key="#userUnique")
    String findGameGroupNameByUserUnique(String userUnique);

    /**
     * 根据userId来找到当前的groupName
     * @param userId
     * @return
     */
    String findCurrentGroupName(String userId);
}
