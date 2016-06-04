package com.cqupt.mis.erp.manager.tool;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;

public interface CommonDao extends BaseDao {

    /**
     * findUserUniqueByUserId 根据某个用户的userId来查找userUnique
     *
     * @param userId
     * @return String
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public String findUserUniqueByUserId(String userId);

    /**
     * findGameGroupNameByUserUnique 根据userUnique 来查找相应的游戏组名称.
     *
     * @param userUnique
     * @return String
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public String findGameGroupNameByUserUnique(String userUnique);

    /**
     * findUsernameByUserUnique
     *
     * @param userUnique
     * @return String
     * @throws
     * @author hhy
     */
    public String findUsernameByUserUnique(String userUnique);

    /**
     * findGroupMemberSize 根据userUnique 来找到游戏组的成员数量
     *
     * @param userUnique
     * @return Integer
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public Integer findGroupMemberSize(String userUnique);

    /**
     * 查询当前进行到的周期时间
     *
     * @param userUnique
     * @return currentTime
     * @author zy
     */
    public Integer findCurrentTime(String userUnique);


    /**
     * 查询竞赛总的比赛年数
     *
     * @param userUnique
     * @return 竞赛总的比赛年数
     * @author 毛家杰
     */
    public Integer findTotalYear(String userUnique);

    /**
     * 查询一年包含的周期数
     *
     * @param userUnique
     * @return 一年包含的周期数
     * @author 毛家杰
     */
    public Integer findPeriodOfYear(String userUnique);

    /**
     * 取出税金比率
     *
     * @author LX
     */
    public float findTax();

    /**
     * 下一周期的存储函数调用
     *
     * @author lx
     */
    public boolean runFunctionInOracle(String userUnique);

    /**
     * findCurrentGroupName 根据userId来找到当前的groupName
     *
     * @param userId
     * @return String
     * @throws
     * @since 1.0.0
     */
    public String findCurrentGroupName(String userId);

    /**
     * findHistoryGameGroupInfo 从his_gamegroup中找到相应的游戏组信息
     *
     * @param gameGroupName
     * @return GameGroupInfo
     * @throws
     * @since 1.0.0
     */
    public GameGroupInfo findHistoryGameGroupInfo(String gameGroupName);

    /**
     * 查看历史记录中的时间.
     *
     * @param userUnique
     * @return
     */
    public Integer findHistoryCurrentTime(String userUnique);

    /**
     * 查看历史记录表中的每年周期数信息.
     *
     * @param userUnique
     * @return
     */
    public Integer findHistoryPeriodOfYear(String userUnique);
}
