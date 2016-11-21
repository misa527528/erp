package com.cqupt.mis.erp.service.advertisement;

import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.vo.AdvertisementUserStatusVO;

import java.util.List;

public interface AdvertisementStatusOfUserService {
	/**
	 * 
	 * findFinishAdvertisementFlagByUserUnique 
	 * 查找是否完成投放广告的状态.
	 * @param userUnique
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	int findFinishAdvertisementFlagByUserUnique(String userUnique);
	
	/**
	 * 取出用户选单状态：1=完成  0=未完成
	 * @author lx
	 * @param userUnique
	 * @return
	 */
	boolean findFinishOrderFlag(String userUnique);
	
	
	/**
	 * 初始化用户选单，广告投放等状态皆为0
	 * @author lx
	 */
	boolean initAdStatusOfUser(String userUnique);

	/**
	 * findAllStatusVOByUserUnique 查找投放广告完广告的用户.
	 * @param userUnique
	 * @return 
	 *List<AdvertisementUserStatusVO>
	 * @exception 
	 * @since  1.0.0
	 */
	List<AdvertisementUserStatusVO> findAllStatusVOByUserUnique(
			String userUnique);

	AdvertisementStatusOfUser findAdvertisementStatusOfUserByUserUnique(String userUnique, int period);
}
