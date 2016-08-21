package com.cqupt.mis.erp.manager.advertisement;

import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.vo.AdvertisementUserStatusVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("AdStatusOfUserDao")
public interface AdStatusOfUserDao {
// @Param("")
    /**
     * 根据userUnique来查找完成的状态值.
     * @param userUnique
     * @param currentTime
     * @return
     */
    int findFinishAdFlag(@Param("userUnique") String userUnique,
                         @Param("currentTime") Integer currentTime);

    /**
     * 修改是否完成的状态
     * @param userUnique
     * @param period 当前的期数
     * @param finishFlag 当前期的广告费投放，0表示未完成，1表示完成
     * @return
     */
    int updateFinishAdFlag(@Param("userUnique") String userUnique,
                           @Param("period") Integer period,
                           @Param("period") Integer finishFlag);

    /**
     * 改变选择订单的标识
     * @param userUnique
     * @param period
     * @param finishFlag
     * @return
     */
    int updateFinishOrderFlag(@Param("userUnique") String userUnique,
                              @Param("period") Integer period,
                              @Param("period")Integer finishFlag);

    /**
     * 根据多个Userunique来查找一堆list出来
     * @param userUniques
     * @param period
     * @return
     */
    // TODO: 2016/8/15 其实也不知道能不能这样传一个userUniques数组进去，测试的时候就知道了
    List<AdvertisementStatusOfUser> findAdvertisementStatusOfUserByUserUniques(
            @Param("userUniques") String[] userUniques, @Param("period")int period);

    /**
     * 查找单个对象的方法
     * @param userUnique
     * @param period
     * @return
     */
    AdvertisementStatusOfUser findAdvertisementStatusOfUserByUserUnique(
            @Param("userUniques") String userUnique, @Param("period")int period);

    /**
     * 批量更新一堆指定用户的status值
     * @param userUniques
     * @param period
     * @param status
     * @return
     */
    // TODO: 2016/8/15 其实也不知道能不能这样传一个userUniques数组进去，测试的时候就知道了
    int updateChooseOrderFlag(@Param("userUniques") String[] userUniques,
                              @Param("period") int period,
                              @Param("status") int status);

    /**
     * 取出用户选单状态：1-完成  0-未完成
     * @param userUnique
     * @return
     */
    Integer findFinishOrderFlag(String userUnique);

    /**
     * 添加用户广告状态
     * @param userUnique
     * @param period 表示时间，总第几期
     * @param finishadflag
     *  表示用户是否完成了当前期的广告费投放，0表示未完成，1表示完成
     * @param finishorderflag
     *  表示用户是否完成了当前期的所有订单选择，0表示未完成，1表示已经完成
     * @param chooseorderflag
     *  表示用户是否可以开始当前期的选订单工作，0表示选单尚未开始，1表示选单正在进行中
     * @return
     */
    int addAdStatusOfUser(@Param("userUnique") String userUnique,
                          @Param("period") int period,
                          @Param("finishadflag") int finishadflag,
                          @Param("finishorderflag") int finishorderflag,
                          @Param("chooseorderflag") int chooseorderflag);

    List<AdvertisementUserStatusVO> findAllStatusVOByUserUnique(String userUnique);
}
