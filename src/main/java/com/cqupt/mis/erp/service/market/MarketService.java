package com.cqupt.mis.erp.service.market;

import com.cqupt.mis.erp.model.market.DevelopedMarket;
import com.cqupt.mis.erp.model.market.DevelopingMarket;
import com.cqupt.mis.erp.model.market.UndevelopMarket;

import java.util.List;

public interface MarketService{
	/**
	 * 
	 * findMarketName 查找所有的market 的名称
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 * @author lx
	 */
	List<String> findMarketName();
	
	/**
	 * 
	 * findUndevelopMarketsByUserUnique 根据userUnique来查找没有开拓的市场
	 * 并且返回一个list对象.
	 * @param userUnique
	 * @return 
	 *List<UndevelopMarket>
	 * @exception 
	 * @author hhy
	 * @since  1.0.0
	 */
	List<UndevelopMarket> findUndevelopMarketsByUserUnique(String userUnique);
	
	
	/**
	 * 
	 * findDevelopMarketsByUserUnique 查找正在开发的市场
	 * @param userUnique
	 * @return 
	 *List<DevelopingMarket>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<DevelopingMarket> findDevelopingMarketsByUserUnique(String userUnique);
	
	/**
	 * 
	 * findDevelopedMarketsByUserUnique 查看已经开拓成功的市场
	 * @param userUnique
	 * @return 
	 *List<DevelopedMarket>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy 
	 */
	List<DevelopedMarket> findDevelopedMarketsByUserUnique(String userUnique);
	
	/**
	 * 
	 * updateUndevelopMarketToDeveloping 开始开拓市场 将 没有开拓的--->转换成开拓中.
	 * 但是这里有一个问题 ! 这里的marketName 妈蛋居然是中文名的.所以只能通过post表单提交过来这个market才能够用.
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *boolean
	 * @exception 
	 * @author hhy
	 * @since  1.0.0
	 */
	boolean updateUndevelopMarketToDeveloping(String userUnique, String marketName);

	/**
	 * 
	 * updateStopUnDevelopingMarket <br/>停止正在开拓的市场 
	 * 主要是将 status 从 1 变成 0
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 *
	 */
	boolean updateStopDevelopingMarket(String userUnique, String marketName);
	
	/**
	 * 
	 * updateStopMaintainDevelopedMarket 停止维护已经开发的市场.
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	boolean updateStopMaintainDevelopedMarket(String userUnique, String marketName);

	/**
	 * updateStartMaintainDevelopedMarket 开始维护某个市场
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	boolean updateStartMaintainDevelopedMarket(String userUnique, String marketName);

	/**
	 * updateStartDevelopingMarket 开始继续开拓市场
	 * @param userUnique
	 * @param marketName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean updateStartDevelopingMarket(String userUnique, String marketName);
	
	
}
