package com.cqupt.mis.erp.service.order;

public interface OrderDeliverService {
	 
	/**
	  * 交货前的总检测，返回
	  * 1：交货成功
	  * 2：交货失败，请首先处理延误时间最长的订单
	  * 3：交货失败，没有足够库存的商品
	  * 
	  * 
	  * @author lx
	  * checkOnTime
	  * @param userUnique
	  * @param orderId
	  * @param
	  * @return
	  */
	 int updateDeliverCheck(String userUnique, String orderId);
	 
	 /**
	  * 检测是否有未按时交货的订单
	  * @return
	  * @author lx
	  */
	 boolean checkOrderOnTime(String userUnique);
	 
	 /**
	  * 想要交货的订单是否正是拖延时间最长的未按时交货的订单  <br>
	  * 1:表示该订单就是拖延时间最长的订单，即是最应该处理的订单！				  <br>
	  * 0:表示该订单不是拖延最长的订单，请先处理最应该处理的订单！
	  * 
	  * @author lx
	  * @param userUnique
	  * @param orderId
	  * @return
	  */
	int checkDelayLongest(String userUnique, String orderId);
	 
	 /**
	  * 1：表示有足够的商品，可以交货				<br>
	  * 0：表示没有足够的商品，不能交货			<br>
	  * 检查库存中是否有足够的产品可供交货
	  * @param userUnique
	  * @param orderId
	  * @return
	  */
	 int checkEnough(String userUnique, String orderId);

	 /**
	  * 开始正式交货处理一系列操作	<br>
	  * 	：订单状态改变  			<br>
	  * 	：会计分录				<br>
	  * 	：产品库存减少			<br>
	  * ------------------------------------<br>
	  * 	param checkOnTime <br>
	  * 	true 表示是未按期交货的订单<br>
	  * 	false表示是按期交货的订单<br>
	  * @author lx
	  * @return
	  */
	 boolean delieverHandle(String userUnique, String orderId, boolean checkOnTime);
	 
	 
}
