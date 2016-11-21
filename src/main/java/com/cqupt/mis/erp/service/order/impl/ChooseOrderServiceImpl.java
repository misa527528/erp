package com.cqupt.mis.erp.service.order.impl;

import com.cqupt.mis.erp.manager.advertisement.AdStatusOfUserDao;
import com.cqupt.mis.erp.manager.iso.DevelopedIsoDao;
import com.cqupt.mis.erp.manager.market.DevelopedMarketDao;
import com.cqupt.mis.erp.manager.order.AllOrdersOfGroupDao;
import com.cqupt.mis.erp.manager.order.ChooseOrderDao;
import com.cqupt.mis.erp.manager.order.OrdersOfUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;
import com.cqupt.mis.erp.model.order.ChooseOrder;
import com.cqupt.mis.erp.model.vo.ChooseOrderVO;
import com.cqupt.mis.erp.service.order.ChooseOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("chooseOrdereService")
public class ChooseOrderServiceImpl  implements ChooseOrderService {
	@Resource
	private AllOrdersOfGroupDao allordersOfGroupDao;
	@Resource
	private DevelopedMarketDao developedMarketDao;
	@Resource
	private DevelopedIsoDao developedIsoDao;
	@Resource
	private ChooseOrderDao chooseOrderDao;
	@Resource
	private OrdersOfUserDao ordersOfUserDao;
	@Resource
	private AdStatusOfUserDao adStatusOfUserDao;
	@Resource
	private GameGroupDao gameGroupDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	
	@Override
	public int updateChoosingOrder(String userUnique, String orderId) {
		AllOrdersOfGroup orders = allordersOfGroupDao.findAllOrdersOfGroupByOrderId(orderId);

        int status = allordersOfGroupDao.findOrderStatusByOrderId(orderId);
		if(status == 0){ //还没有被获取的订单
			if(!(developedMarketDao.findDevelopedMarket(userUnique, orders.getMarketName()) == null)){
				//这里本来要判断这个产品是否已经开发完成..但是后面要改成能够提前选择没有开发完成的产品所以就不做这里的判断 .

				if(!(developedIsoDao.findOneDevelopedISO(userUnique, orders.getSpecialRem()) == null)){
					
					//判断这张单是否轮到这个user选择
					if(isUserUniqueCanChoosing(userUnique,orders)){
						//chooseOrderDao.refreshChooseOrderCache();
						String orderID = orders.getOrderID();
						String productName = orders.getProductName();
						Double price = orders.getPrice();
						Integer pNumber = orders.getpNumber();
						String marketName = orders.getMarketName();
						Integer needTime = orders.getNeedTime();
						Integer moneyTime = orders.getMoneyTime();
						Double penalPercent = orders.getPenalPercent();
						String specialRem = orders.getSpecialRem();
						Integer endTime = null;

						allordersOfGroupDao.updateOrderStatusByOrderId(orderId,1);
						ordersOfUserDao.addOrdersOfUser(userUnique, orderID, productName, price, pNumber, marketName,
								needTime, moneyTime, penalPercent, specialRem, endTime);

                        if (marketName == null || "".equals(marketName)){
                            marketName = "%";
                        }
                        if (productName == null || "".equals(productName)){
                            marketName = "%";
                        }
						chooseOrderDao.updateChooseOrderValue(userUnique,orders.getPeriod(),
								gameGroupDao.findGroupMemberSize(userUnique),marketName, productName);
						return 1;
					}else{
						//没有轮到这个用户选择,不能选择这张单.
						return 2;
					}
				}else{
					//iso验证没有研发成功,选单失败.
					return 3;
				}
			}else{
				//市场没有开发完成,不能选择这个市场的订单
				return 4;
			}
		}else{ // status == 1:订单已经被获取；status == 1:找不到就返回-1
			return 5;
		}
		
	}

	private boolean isUserUniqueCanChoosing(String userUnique,AllOrdersOfGroup orders){
		String chooserUser = chooseOrderDao.findChooseOrderUser(
				orders.getMarketName(), orders.getProductName(), orders.getPeriod(), orders.getGroupName());
		if(userUnique!=null && chooserUser!=null){
			if(userUnique.equals(chooserUser)) return true;
		}
		return false;
	}

	@Override
	public int updateEndChooseOrder(String userUnique) {
		Integer period = gameGroupMemberDao.findCurrentTime(userUnique);
		AdvertisementStatusOfUser adStatusOfUser =
				adStatusOfUserDao.findAdvertisementStatusOfUserByUserUnique(userUnique, period);
		
		if(adStatusOfUser.getFinishAdvertiseFlag()==1){
			//已经投放完广告.
			if(adStatusOfUser.getFinishOrderFlag()==0){
				adStatusOfUserDao.updateFinishOrderFlag(userUnique, period, 1);
				chooseOrderDao.updateChooseOrderValueByUserUnique(1000, userUnique);
				return 1;
			}else{
				//已经完成这一期的选择订单的操作了.
				return 2;
			}
		}else{
			//如果不等于 1 就是没有完成投放广告
			return 3;
		}
	}

	@Override
	public void updateChooseOrderValue(String orderId, Integer chooseValue) {
		chooseOrderDao.updateChooseOrderValueByOrderId(orderId, chooseValue);
	}

	@Override
	public List<ChooseOrder> findAllChooseOrderByUserUnique(String userUnique) {
		return chooseOrderDao.findChooseOrderByUserUnique(userUnique);
	}

	@Override
	public boolean udpateIsClooseAll(String userUnique) {
		List<ChooseOrder> chooseOrderList = chooseOrderDao.findChooseOrderByUserUnique(userUnique);
		if(chooseOrderList.size()<1){
			this.updateEndChooseOrder(userUnique);
			return true;
		}
		return false;
	}

	@Override
	public List<ChooseOrderVO> findChooseOrderByMarketNameAndProductName(String userUnique, String marketName, String productName) {
		String gameGroupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		int period = gameGroupMemberDao.findCurrentTime(userUnique);

        if (marketName == null || "".equals(marketName)){
			marketName = "all";
		}
		if (productName == null || "".equals(productName)){
			productName = "all";
		}
		return chooseOrderDao.findChooseOrderByMarketNameAndProductNameCacheWithGroup(
				period,gameGroupName,marketName,productName,userUnique);
	}

}
