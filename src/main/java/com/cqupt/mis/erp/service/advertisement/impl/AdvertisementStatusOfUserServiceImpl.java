package com.cqupt.mis.erp.service.advertisement.impl;

import com.cqupt.mis.erp.manager.advertisement.AdStatusOfUserDao;
import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import com.cqupt.mis.erp.model.vo.AdvertisementUserStatusVO;
import com.cqupt.mis.erp.service.advertisement.AdvertisementStatusOfUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service("advertisementStatusOfUserService")
public class AdvertisementStatusOfUserServiceImpl implements AdvertisementStatusOfUserService {
	@Resource
	private AdStatusOfUserDao adStatusOfUserDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private ApprovedUserDao approvedUserDao;
	
	@Override
	public int findFinishAdvertisementFlagByUserUnique(String userUnique) {
		Integer currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
		return adStatusOfUserDao.findFinishAdFlag(userUnique, currentTime);
	}

	@Override
	public boolean findFinishOrderFlag(String userUnique) {
		int temp = adStatusOfUserDao.findFinishOrderFlag(userUnique);
		if(temp == 1) {
			return true;
		} else if(temp == 0) {
			return false;
		} else {
			return false;
		} 
		
	}

	@Override
	public boolean initAdStatusOfUser(String userUnique) {
		int period = gameGroupMemberDao.findCurrentTime(userUnique);
		int finishadflag = 0;
		int finishorderflag = 0;
		int chooseorderflag = 0;

		int result = adStatusOfUserDao.addAdStatusOfUser(userUnique,
				period, finishadflag, finishorderflag, chooseorderflag);

        return result == 1;
    }

	
	@Override
	public List<AdvertisementUserStatusVO> findAllStatusVOByUserUnique(String userUnique) {
		//获取还没有破产的成员  这里的1 代表是  用户的status
		List<GameGroupMemberInfo> gameGroupList = gameGroupMemberDao.findGameGroupMemberListByStatus(1, userUnique);
		List<AdvertisementUserStatusVO> person = adStatusOfUserDao.findAllStatusVOByUserUnique(userUnique);
		//这里将没有的人也不全上去
		for(GameGroupMemberInfo g : gameGroupList){
			boolean flag = false;
			for(AdvertisementUserStatusVO ad : person){
				if(ad.getUserId().equals(g.getUserID())){
					flag = true;
				}
			}
			if(!flag){
				AdvertisementUserStatusVO adVo = new AdvertisementUserStatusVO();
				adVo.setName(approvedUserDao.findUsernameByUserUnique(g.getUserUnique()));
				adVo.setStatus(0);
				adVo.setUserId(g.getUserID());
				person.add(adVo);
			}
		}
		return person;
	}

	@Override
	public AdvertisementStatusOfUser findAdvertisementStatusOfUserByUserUnique(String userUnique, int period) {
		return adStatusOfUserDao.findAdvertisementStatusOfUserByUserUnique(userUnique, period);
	}


}
