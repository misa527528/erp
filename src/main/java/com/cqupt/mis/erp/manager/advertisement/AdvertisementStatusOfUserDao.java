package com.cqupt.mis.erp.manager.advertisement;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.vo.AdvertisementUserStatusVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("advertisementStatusOfUserDao")
public interface AdvertisementStatusOfUserDao extends BaseDao {

    /**
     * findFinishFlag  根据userUnique来查找完成的状态值.
     *
     * @param userUnique
     * @return int
     * @throws
     * @since 1.0.0
     */
    public int findFinishAdFlag(String userUnique);

    /**
     * updateFinishFlag 修改是否完成的状态
     *
     * @param userUnique 用户的唯一标识
     * @param period     当前的期数
     * @param finishFlag 当前期的广告费投放，0表示未完成，1表示完成
     *                   void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateFinishAdFlag(String userUnique, Integer period, Integer finishFlag);


    /**
     * updateFinishOrderFlag 改变选择订单的标识
     *
     * @param userUnique
     * @param period
     * @param finishFlag void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateFinishOrderFlag(String userUnique, Integer period, Integer finishFlag);

    /**
     * findAdvertisementStatusOfUserByUserUniques 根据多个Userunique来查找一堆list出来
     *
     * @param userUniques
     * @param period
     * @return List<AdvertisementStatusOfUser>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<AdvertisementStatusOfUser> findAdvertisementStatusOfUserByUserUniques(String[] userUniques, int period);

    /**
     * findAdvertisementStatusOfUserByUserUnique 查找单个对象的方法.
     *
     * @param userUnique
     * @param period
     * @return AdvertisementStatusOfUser
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public AdvertisementStatusOfUser findAdvertisementStatusOfUserByUserUnique(String userUnique, int period);

    /**
     * updateChooseOrderFlag  批量更新一堆指定用户的status值
     *
     * @param userUniques
     * @param period
     * @param status      void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateChooseOrderFlag(String[] userUniques, int period, int status);

    /**
     * 取出用户选单状态：1-完成  0-未完成
     *
     * @param userUnique
     * @return
     * @author lx
     */
    public Integer findFinishOrderFlag(String userUnique);

    /**
     * 添加用户广告状态
     */
    public void addAdStatusOfUser(AdvertisementStatusOfUser advertisementStatusOfUser);

    /**
     * findAllStatusVOByUserUnique
     *
     * @param userUnique
     * @return List<AdvertisementUserStatusVO>
     * @throws
     * @since 1.0.0
     */
    public List<AdvertisementUserStatusVO> findAllStatusVOByUserUnique(String userUnique);


}
