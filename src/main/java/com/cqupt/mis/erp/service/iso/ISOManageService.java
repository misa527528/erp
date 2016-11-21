package com.cqupt.mis.erp.service.iso;

import com.cqupt.mis.erp.model.iso.ISOBasic;
import com.cqupt.mis.erp.model.iso.ISODeveloped;
import com.cqupt.mis.erp.model.iso.ISODeveloping;
import com.cqupt.mis.erp.model.iso.ISOUndevelop;

import java.util.List;

public interface ISOManageService {
	
	/**
	 * 查看所有未开拓的iso
	 * @author zy
	 * @param userUnique
	 * @return
	 */
	List<ISOUndevelop> findAllISOUndevelop(String userUnique);
	
	/**
	 * 查看所有开发中的iso
	 * @author zy
	 * @param userUnique
	 * @return
	 */
	List<ISODeveloping> findAllISODeveloping(String userUnique);
	
	/**
	 *查看单个未开拓的iso
	 * @author zy 
	 * @param userUnique
	 * @param isoName
	 * @return
	 */
	ISOUndevelop findOneUndevelop(String userUnique, String isoName);
	
	/**
	 * 开拓iso
	 * @author zy 
	 * @param userUnique
	 * @param isoName
	 * @return
	 */
	boolean addISOToISODeveloping(String userUnique, String isoName);
	
	
	/**
	 * 恢复开发某个iso认证
	 * @author zy 
	 * @param userUnique
	 * @param isoName
	 * @return
	 */
	boolean updateISODevelopingStatusToOne(String userUnique, String isoName);
	
	/**
	 * 停止开发某个iso认证
	 * @author zy 
	 * @param userUnique
	 * @return
	 */
	boolean updateISODevelopingStatusToZero(String userUnique, String isoName);
	
	/**
	 *查看所有已开发的iso认证
	 * @author zy 
	 * @param userUnique
	 * @return
	 */
	List<ISODeveloped> findAllISODeveloped(String userUnique);
	
	/**
	 * 向已开拓iso中增加一条记录
	 * ISOManageAction
	 * @author zy
	 * @param
	 * @return
	 */
	boolean addISOToISODeveloped(String userUnique, String isoName);
	
	/**
	 * 恢复对停止维护的已开发ISO的维护
	 * @author zy 
	 * @param userUnique
	 * @param isoName
	 * @return
	 */
	boolean updateISODevelopedStatusToOne(String userUnique, String isoName);
	
	/**
	 * 停止已开发ISO的维护
	 * @author zy 
	 * @param userUnique
	 * @return
	 */
	boolean updateISODevelopedStatusToZero(String userUnique, String isoName);
	
	/**
	 * 取出所有ISO认证基础信息
	 * @author LX 
	 * @return
	 */
	List<ISOBasic> findAllISOBasic();
	
	/**
	 * 取出基础ISO认证的条数
	 * @author LX 
	 * @return
	 */
	Integer findISOBasicNum();
}
