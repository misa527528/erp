package com.cqupt.mis.erp.service.order.impl;

import com.cqupt.mis.erp.manager.finance.TaxRateBasicDao;
import com.cqupt.mis.erp.manager.product.ProductOfUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.order.OrdersOfUser;
import com.cqupt.mis.erp.model.product.ProductOfUser;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.service.finance.fund.PenaltyCashService;
import com.cqupt.mis.erp.service.finance.fund.TaxSheetService;
import com.cqupt.mis.erp.service.finance.fund.WillReceiveService;
import com.cqupt.mis.erp.service.order.OrderDeliverService;
import com.cqupt.mis.erp.service.order.OrdersOfUserService;
import com.cqupt.mis.erp.service.product.ProductBasicService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("orderDeliverService")
public class OrderDeliverServiceImpl implements OrderDeliverService {
	
	@Resource
	private OrdersOfUserService ordersOfUserService;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private AccountHeadService accountHeadService;
	@Resource
	private AccountDetailService accountDetailService;
	@Resource
	private TaxSheetService taxSheetService;
	@Resource
	private TaxRateBasicDao taxRateBasicDao;
	@Resource
	private ProductBasicService productBasicService;
	@Resource
	private PenaltyCashService penaltyCashService;
	@Resource
	private CashSheetService cashSheetService;
	@Resource
	private WillReceiveService willReceiveService;
	@Resource
	private ProductOfUserDao productOfUserDao;
	
	
	
	@Override
	public int updateDeliverCheck(String userUnique, String orderId) {
		
	    //System.out.println("交货的订单为："+orderId);
		boolean C = this.checkOrderOnTime(userUnique);
		//System.out.println(C);
		if(C) {
			//表示有未按期交货的订单，则转步骤2检查想要交货的订单是否为拖延时间最长的未按时交货的订单
				int checkDelayLongest = this.checkDelayLongest(userUnique, orderId);	
				if(checkDelayLongest == 0) {
				    //如果该订单不是延误时间最长的订单，提示“请首先处理延误时间最长的订单！”，维持用户界面为当前界面，处理结束
					//System.out.println("请首先处理延误时间最长的订单");
					return 2;
				}else {
				    //当前处理的订单即为拖延时间最长的未交货订单，转步骤3检查库存中是否有足够的产品可供交货
					int checkEnough = this.checkEnough(userUnique, orderId);	
					if(checkEnough == 0) {
						//如果库存不够交货，则提示“库存中没有足够的产品可供交货！”，维持用户界面为当前界面，处理结束。
						//System.out.println("库存中没有足够的产品可供交货！");
						
						return 3;
						} else {
							//如果库存足够交货，则转入步骤四，完成交货工作
							boolean deliver = this.delieverHandle(userUnique, orderId,C);
							if(deliver) {
							//填写会计分录等数据库操作
								//System.out.println("会计分录填写成功");
							} else {
								//System.out.println("会计分录填写失败，交货失败");
								return 0;
							}
					}	
				}
				
		}else {
			
			//表示没有未按期交货的订单,则转步骤3检查库存中是否有足够的产品可供交货
			int checkEnough = this.checkEnough(userUnique,orderId);	
			//System.out.println(checkEnough);
			if(checkEnough==0) {
				//如果库存不够交货，则提示“库存中没有足够的产品可供交货！”，维持用户界面为当前界面，处理结束。
				//System.out.println("库存中没有足够的产品可供交货！");
				return 3;
			} else {
				//如果库存足够交货，则转入步骤四，完成交货工作
				boolean deliver = this.delieverHandle(userUnique, orderId, C);
				if(deliver) {
					//填写会计分录等数据库操作
					//System.out.println("会计分录填写成功");
				} else {
					//System.out.println("会计分录填写失败，交货失败");
					return 0;
				 }
			}
		}					
		return 1;
	}

	@Override
	public boolean checkOrderOnTime(String userUnique) {
		//用于表示是否有未按期交货的订单，C为True表示有未按期交货的订单，C为False表示没有未按期交货的订单
		  boolean C = false;
		  //取出最早交货但是没有还没交货的订单
		  int SmallestTime = ordersOfUserService.findSmallestTime(userUnique);
		  //从游戏用户表中
		  int GCPeriod = gameGroupMemberDao.findCurrentPeriod(userUnique);
		 
		  if(SmallestTime < GCPeriod) {
			  //如果最小值比CurrentPeriod小，表示没有未按期交货的订单
			  C = true; 
		  }
		  return C;
	}
	
	@Override
	public int checkDelayLongest(String userUnique, String orderId) {
		int SmallestTime = ordersOfUserService.findSmallestTime(userUnique);
	    int NeedTime = ordersOfUserService.findOrderDetail(orderId).getNeedTime();
	    if(SmallestTime == NeedTime) {
			  //交货日期最小值与订单的交货日期相等，表示当前处理的订单即为拖延时间最长的未交货订单
			  return 1;
		  } else {
			//交货日期最小值与订单的交货日期不相等，则应首先处理延误时间最长的订单
			  return 0;
		  }  
	}

	@Override
	public int checkEnough(String userUnique, String orderId) {
		String productName; 
		int pNumber = 0;			//用户的商品的库存量
		int pNumberOfOrder = 0;		//订单上商品的需求量
		OrdersOfUser order = ordersOfUserService.findOrderDetail(orderId);
		pNumberOfOrder = order.getpNumber();
		productName = order.getProductName();
		
		ProductOfUser productOfUser = new ProductOfUser();
		productOfUser.setUserUnique(userUnique);
		productOfUser.setProductName(productName);
		
		pNumber = productOfUserDao.findProductOfUserPNumber(userUnique, productName);
		if(pNumber >= pNumberOfOrder) {
			  //如果用户拥有的产品的数量大于或等于应交货的交货数量，则可以交货
			  return 1;
		} else {
			  //如果用户拥有否认产品的数量小于应交货的交货数量，则返回0
			  return 0;  
		 }	   
	}

	@Override
	// TODO: 2016/8/24 check一下看看是不是应该重构
	public boolean delieverHandle(String userUnique, String orderId, boolean checkOnTime) {
	
		String productName = ordersOfUserService.findOrderDetail(orderId).getProductName();
		//System.out.println("产品名为："+productName);
		//填写订单交货日期
		OrdersOfUser ordersOfUser = ordersOfUserService.findOrderDetail(orderId);
		ordersOfUserService.updateOrderEndTime(ordersOfUser);
		
		float A= ordersOfUserService.findSaleRoom(orderId);//订单产生的卖价
		//System.out.println("出售商品的收入A为："+A);
		int happenTime = gameGroupMemberDao.findCurrentPeriod(userUnique);//当前时期
		
		//插入第一条会计分录信息（出售商品收入） 。
		accountHeadService.addAccountHead(userUnique, happenTime, "出售产品");
		accountDetailService.addAccountDetail(
				userUnique, "应收账款", "主营业务收入", "借", "贷", (double)A, (double)A, (double)A);
		
		//在应收账款表WILLRECEIVE中增加一条记录
		int endTime = ordersOfUser.getMoneyTime();
		willReceiveService.addWillReceive(
				userUnique, (double)A, happenTime, happenTime + endTime, "按订单交货产生的应收账款", "");
		//lx:四舍五入处理float数
		//插入第二条会计分录信息(缴纳的税金)。
		Float TT = ERPUtils.round((A * taxRateBasicDao.findTaxRate()));
System.out.println("税率为:"+ taxRateBasicDao.findTaxRate()+"   商品的卖价:"+A+"  缴纳的税金TT为："+TT);
System.out.println(taxRateBasicDao.findTaxRate());
System.out.println(A);
System.out.println(TT);
		accountHeadService.addAccountHead(userUnique, happenTime, "销售产品的营业税");
		accountDetailService.addAccountDetail(
                userUnique, "主营业务税金", "应交税金", "借", "贷", (double)TT, (double)TT, (double)TT);

		//增加税金表中的数值
		taxSheetService.updateTaxSheet(userUnique, TT);

		//插入第三条会计分录(主营业务成本相关)。
		float B = productBasicService.findProductPrice(productName);//产品成本
		
		//2015.1.16 何海源王永对比测试.
		//获取订单中产品的数量,然后  原料的    单价*数量 得到    原料成本金额
		OrdersOfUser ordersOfUser1 = ordersOfUserService.findOrderDetail(orderId); 
		//lx:四舍五入处理
		B = ERPUtils.round(B*ordersOfUser1.getpNumber());
		
		
		accountHeadService.addAccountHead(userUnique, happenTime, "出售产品的成本核算");
		accountDetailService.addAccountDetail(
                userUnique, "主营业务成本", "产品", "借", "贷", (double)B, (double)B, (double)(-B));
		
		//减少商品的库存数量
		int orderPNumber = ordersOfUserService.findOrderDetail(orderId).getpNumber();//订单上需要的产品数量
        int pNumber = orderPNumber; //此处设置的PNumber是用户要减少的产品数
		productOfUserDao.updateDecreasePNumber(userUnique, productName, pNumber);
		
		//插入第四条会计分录(其他业务支出)。在未按时交货，违约的情况下才执行
		if(checkOnTime) {
			double D = penaltyCashService.computePenaltyCash(orderId);//未按时交货的违约金---还没方法提供，等待中！
			accountHeadService.addAccountHead(userUnique, happenTime, "订单未按时交货的违约金");
			accountDetailService.addAccountDetail(userUnique, "其他业务支出", "现金", "借", "贷", D, D, (-D));
			//将现金表CASHSHEET中的Cash字段值减少D
			cashSheetService.updateSubtractCash(userUnique, D);
		}
		
		return true;
	}
	
	
	 
	
}
