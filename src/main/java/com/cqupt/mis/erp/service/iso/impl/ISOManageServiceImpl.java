package com.cqupt.mis.erp.service.iso.impl;

import com.cqupt.mis.erp.manager.iso.DevelopedIsoDao;
import com.cqupt.mis.erp.manager.iso.DevelopingIsoDao;
import com.cqupt.mis.erp.manager.iso.IsoBasicDao;
import com.cqupt.mis.erp.manager.iso.UndevelopIsoDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.iso.ISOBasic;
import com.cqupt.mis.erp.model.iso.ISODeveloped;
import com.cqupt.mis.erp.model.iso.ISODeveloping;
import com.cqupt.mis.erp.model.iso.ISOUndevelop;
import com.cqupt.mis.erp.service.iso.ISOManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("iSOManageService")
public class ISOManageServiceImpl implements ISOManageService{
	@Resource
	private IsoBasicDao isoBasicDao;
	@Resource
	private UndevelopIsoDao undevelopIsoDao;
	@Resource
	private DevelopingIsoDao developingIsoDao;
	@Resource
	private DevelopedIsoDao developedIsoDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;



	@Override
	public boolean addISOToISODeveloping(String userUnique, String isoName) {
		int beginTime= gameGroupMemberDao.findCurrentTime(userUnique);
		ISOUndevelop unIso=undevelopIsoDao.findOneUndevelop(userUnique, isoName);
        int researchPeriod = unIso.getResearchPeriod();
        double researchCost = unIso.getResearchCost();
        int finishedPeriod = 0;
        int status = 1;

		developingIsoDao.addIsoToISODeveloping(userUnique, isoName,
                researchPeriod, researchCost, finishedPeriod, beginTime, status);
		undevelopIsoDao.delelteISOUndevelop(userUnique, isoName);
		return true;
	}
	@Override
	public List<ISOUndevelop> findAllISOUndevelop(String userUnique) {
		return undevelopIsoDao.findAllISOUndevelop(userUnique);
	}
	@Override
	public ISOUndevelop findOneUndevelop(String userUnique, String isoName) {
		return undevelopIsoDao.findOneUndevelop(userUnique, isoName);
	}
	@Override
	public boolean updateISODevelopingStatusToOne(String userUnique,
			String isoName) {
		developingIsoDao.updateISODevelopingStatus(userUnique, isoName, 1);
		return true;
	}
	@Override
	public boolean updateISODevelopingStatusToZero(String userUnique,
			String isoName) {
		developingIsoDao.updateISODevelopingStatus(userUnique, isoName, 0);
		return true;
	}
	@Override
	public List<ISODeveloped> findAllISODeveloped(String userUnique) {
		return developedIsoDao.findAllISODeveloped(userUnique);
	}
	@Override
	public boolean updateISODevelopedStatusToOne(String userUnique,
			String isoName) {
		developedIsoDao.updateISODevelopedStatus(userUnique, isoName, 1);
		return true;
	}
	@Override
	public boolean updateISODevelopedStatusToZero(String userUnique,
			String isoName) {
		developedIsoDao.updateISODevelopedStatus(userUnique, isoName, 0);
		return true;
	}
	@Override
	public boolean addISOToISODeveloped(String userUnique, String isoName) {
		ISOBasic isoBasic=isoBasicDao.findOneISOBasic(isoName);
        int status = 1;
        int endTime= gameGroupMemberDao.findCurrentTime(userUnique);
        int beginTime = endTime;
        int lastStatus = 0;
        Double maintainCost = isoBasic.getMaintainCost();

		developedIsoDao.addISOToISODeveloped(userUnique, isoName, status,
                beginTime, endTime, lastStatus, maintainCost);
		developingIsoDao.deleteDevelopingISO(userUnique, isoName);
		return true;
	}
	@Override
	public List<ISODeveloping> findAllISODeveloping(String userUnique) {
		return developingIsoDao.findAllISODeveloping(userUnique);
	}
	
	@Override
	public List<ISOBasic> findAllISOBasic() {
		return isoBasicDao.findAllISOBasic();
	}

	@Override
	public Integer findISOBasicNum() {
		return isoBasicDao.findISOBasicNum();
	}
}
