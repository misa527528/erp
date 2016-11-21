package com.cqupt.mis.erp.service.inigame.impl;

import com.cqupt.mis.erp.manager.inigame.IniGameDao;
import com.cqupt.mis.erp.manager.prediction.OrderPredictionBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.model.iso.ISOBasic;
import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;
import com.cqupt.mis.erp.model.order.OrderPredictionBasic;
import com.cqupt.mis.erp.model.prediction.PredictionOfGroup;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.service.inigame.IniGameService;
import com.cqupt.mis.erp.service.iso.ISOManageService;
import com.cqupt.mis.erp.service.market.MarketService;
import com.cqupt.mis.erp.service.order.AllOrdersOfGroupService;
import com.cqupt.mis.erp.service.prediction.PredictionOfGroupService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component("iniGameService")
public class IniGameServiceImpl implements IniGameService{
	
	@Resource
	private IniGameDao iniGameDao;
	@Resource
	private MarketService marketService;
	@Resource
	private GameGroupService gameGroupService;
	@Resource
	private OrderPredictionBasicDao orderPredictionBasicDao;
	@Resource
	private PredictionOfGroupService predictionOfGroupService;
	@Resource
	private AllOrdersOfGroupService allOrdersOfGroupService;
	@Resource
	private ISOManageService iSOManageService;
	@Resource
	private GameGroupMemberService gameGroupMemberService;
	@Resource
	private GameGroupDao gameGroupDao;
	
	/**
	 * 初始化会总接口
	 * @author LX 
	 */
	@Override
	public boolean iniGame(String groupName) {
		//updateUserUnqiqueAndCurrentPeriod(groupName) && 
		return iniGameByPLSQL(groupName) && initPredictionOfGroup(groupName)
				&& iniGroupOrder(groupName) && iniOrderISO(groupName) ;
	}
	
	
	/**
	 * 初始化数据库存储过程
	 */
    // TODO: 2016/8/22 需要检查存储过程的返回值，是不是成功的返回值>1
    @Override
	public boolean iniGameByPLSQL(String groupName) {
		int result = 0;
		
		int returnResult = iniGameDao.iniGameByPLSQL(groupName, result);
		
		return returnResult > 0;
	}
	
	
	/**
	 * @author lx
	 * 初始化市场预测，取出游戏组总共年+所有市场名字+订单基础预测+随机数 = 终极的预测（PredivtionOfGroup）！
	 */
	@Override
	public boolean initPredictionOfGroup(String groupName) {
		//获得游戏组对应总年数信息
		int gameYears = gameGroupService.findYearsByGroupName(groupName);
		//取出所有市场名称
		List<String> markets = marketService.findMarketName();
		//获取辅助产生订单和市场预测的表中的信息 
		List<OrderPredictionBasic> orderPredictionBasics = orderPredictionBasicDao.findAllOrderPredictionBasic();
		
		List<PredictionOfGroup> PredictionOfGroups = new ArrayList<PredictionOfGroup>();
		
		for (int i = 0; i < orderPredictionBasics.size(); i++) {
			for (int j = 0; j < markets.size(); j++) {
				for (int k = 1; k <= gameYears; k++) {
					Random r = new Random();
					int r1 = r.nextInt(3) - 1;
					int r3 = r.nextInt(3) - 1;
					double r2 = Math.random() - Math.random();
					double r4 = Math.random() - Math.random();
					
					
					OrderPredictionBasic opb = orderPredictionBasics.get(i);
					double price = opb.getPrice() + opb.getPriceDifference() * r1 + opb.getPrice() * r2 * opb.getPriceFloat();
					double mount = opb.getMount() + opb.getMountDifference() * r3 + opb.getMount() * r4 * opb.getMountFloat();
					if (price <= 0) {
						price = opb.getPrice() / 2;
					}
					if (mount <= 0) {
						mount = opb.getMount() / 2;
					}
					price = (int)price;//price本身是double，但是原始第一版代码里插入数据库时讲price强制变为了int
					//初始化单个市场预测订单
					PredictionOfGroup pog = new PredictionOfGroup();
					pog.setGroupName(groupName);
					pog.setPeriod(k);
					pog.setMarketName(markets.get(j));
					pog.setProductName(orderPredictionBasics.get(i).getProductName());
					pog.setPrice(price);
					pog.setMount((int)mount);
					//System.out.println("产品：" + pog.getProductName() + "，在" + pog.getMarketName() + "，第" + pog.getPeriod() + "年" + "的单价=" + pog.getPrice() + "  ，数量=" + pog.getMount());
					
					
					PredictionOfGroups.add(pog);
					
					
				}
				
			}
		}
		//saveList
		predictionOfGroupService.addPredictionOfGroups(PredictionOfGroups);
		System.out.println("市场预测初始化完成！");
		return true;
	}

	
	/**
	 * @author lx
	 * 初始化订单
	 * 取出所有市场预测信息 + 游戏组内信息 = 随机生成多张订单
	 */
	public boolean iniGroupOrder(String groupName) {
		//获得所有的预测信息
		List<PredictionOfGroup> predictions = predictionOfGroupService.findAllPredictionOfGroup(groupName);
		//获得游戏组的信息. 
		GameGroupInfo gameGroup = gameGroupService.findGameGroupInfoByGroupName(groupName);
		
		PredictionOfGroup prediction = null;
		
		ArrayList<AllOrdersOfGroup> allOrdersOfGroups = new ArrayList<AllOrdersOfGroup>();
		
		for(int i = 0; i < predictions.size(); i++){
			prediction = predictions.get(i);
			//(1)s表示已添加到订单表  中的 商品个数
			Integer s = 0;
			//设置 orderNums    为 游戏组中人数的1.5倍
			Double orderNums = 1.5 * gameGroup.getUserNumbers(); //未来可以将 1.5 变成调控参数
			
			//System.out.println("初始化订单方法内：每个市场每个产品该产生的订单数" + orderNums);
			
			for(int j = 0; j < orderNums; j++){
				//(2)计算N值
				//Integer n = ((Long)Math.round(Math.floor(prediction.getMount() / orderNums))).intValue();
				Integer n = ((Double)(prediction.getMount() / orderNums)).intValue(); 
				if(n <= 0){
					n = 1;
				}
				//(3)计算pp ,pp 表示订单中商品的单价
				Random random = new Random();
				
				Double pp = prediction.getPrice() + (random.nextInt(3) - 1) + (random.nextInt(3) - 1) * 0.5;
				if(pp <= 0){
					pp = 1.0;
				}
				//(4)计算nn ,nn表示 订单中产品的个数
				//2015.6.11 何海源和王永修改 
				//Integer nn = n + (random.nextInt(3) - 1) + ((Double)((random.nextInt(3) - 1) * 0.5)).intValue();
				Integer nn = n + (random.nextInt(3) - 1);
				if(nn <= 0){
					nn = 1;
				}
				
				//(5)随机产生交货日期
				Integer t1 = prediction.getPeriod() * gameGroup.getPeriodsOfOneYear() + random.nextInt(4) + 2;
				if(t1 > gameGroup.getPeriodsOfOneYear() * gameGroup.getYears()){
					t1 = gameGroup.getPeriodsOfOneYear() * gameGroup.getYears();
				}
				
				//(6)随机设置到账日期
				Integer t2 = random.nextInt(5) + 2;
				
				//(7)计算 s = s + nn
				s = s + nn;
				
				//(8)如果s>f, 转步骤3；
				//累加到预测个数后 将不再添加订单. 
				if(s <= prediction.getMount()){
					/*(9)向表ALLORDERSOFGROUP表中加入新记录*/
					AllOrdersOfGroup order = new AllOrdersOfGroup();
					order.setAllOrdersOfGroup(prediction.getProductName(), nn, pp, prediction.getMarketName(), t1,t2, 0.05, null, prediction.getGroupName(), 0, (prediction.getPeriod() - 1) * gameGroup.getPeriodsOfOneYear() + 1);
					
					allOrdersOfGroups.add(order);
					//输出信息，用来检测预测信息
					/*System.out.println(prediction.getProductName()+"  " + nn +"   " + pp +"   " + prediction.getMarketName() +"   " + t1 +"   " + 
							t2 +"   " + 0.05 +"   " + null +"   " +  prediction.getGroupName() +"   " +  0 +"   " + ((prediction.getPeriod() - 1) * gameGroup.getPeriodsOfOneYear() + 1));*/
				}else{
					break;
				}
			}
			/*步骤3 继续循环，直到结束*/
		}
		//将生成的初始化订单批量插入表中
		
		
		if(allOrdersOfGroups.size() > 0){			
			allOrdersOfGroupService.addAllOrdersOfGroups(allOrdersOfGroups);
		}
		
		return true;
	}
	
	
	/**
	 * @author lx
	 * 初始化订单的ISO认证
	 * 
	 */
	public boolean iniOrderISO(String groupName) {
		//获得所有的预测信息
		List<AllOrdersOfGroup> orders = allOrdersOfGroupService.findAllOrdersOfGroup(groupName);
		Integer years = gameGroupService.findYearsByGroupName(groupName);
		int periodOfYear = gameGroupService.findGameGroupInfoByGroupName(groupName).getPeriodsOfOneYear();

		//iso类型数量
		Integer isoNum = iSOManageService.findISOBasicNum();
		//iso基本信息
		List<ISOBasic> isos = iSOManageService.findAllISOBasic();
		
		/*步骤三 依次取出RS中的每一天记录，得到其Period值，设为y。*/
		for(int i = 0; i < orders.size(); i++) {
			AllOrdersOfGroup order = orders.get(i);
			
			Integer period = order.getPeriod(); //订单上周期数
			
			//System.out.println("iniOrderISO方法内：RS中的每一天记录，得到其Period值:::" + period);
			
			Integer r1 = 0;
			Random ran = new Random();
			
			
			//何海源和王永修改原来的算法, 重新设置ISO 出现的出现, 会按照一个线性的方程不断的递减    
			//  x = 10+(y-2)*40;  (   x~(0,100), y 是年份 , 30 是自定义的一个增长趋势 );
			//到了100 之后就应该要无限趋向于 90% 的比例
			int ordersYears = (period/periodOfYear)+1;
			if(ordersYears > 1){
				int x = 10+(ordersYears - 2 )*30;
				if(x>90){
					x = 90;
				}
				if(ran.nextInt(100)>x){
					r1 = 1;
				}else {
					r1 = 0;
				}
			}else {
				r1 = 1;
			}
			
			
			//如果R1为0，则产生一个[1, z]区间内随机整数R2
			if(r1 == 0){
				Integer r2 = ran.nextInt(isoNum) + 1;
				
				//System.out.println("iniOrderISO方法内：如果R1为0，则产生一个[1, z]区间内随机整数R2:::" + r2);
				
				//从ISOBASIC表中取出第R2个记录的ISOName字段值
				String isoName = isos.get(r2 - 1).getIsoName();
				
				//System.out.println("iniOrderISO方法内：ISOName:::" + isoName);
				
				//RS中对应记录的SpecialRem字段值,设置为ISOName字段的值
				order.setSpecialRem(isoName);
				
				//何海源 和王永 修改 增加iso 之后的价格应该要提高. 
				//这里加上了认证之后应该要相应的增加每一个的价格. 
				if(isos.get(0).getIsoName().equals(isoName)){
					//因为是按照开拓时间来排序的, 第一个拿出来的应该会是 ISO900,这个时候应该单价增加0.5
					order.setPrice(order.getPrice() + 0.5);
				}else {
					//这里应该是第二个这个时候应该是 ISO1400 单价需要提高 1.
					order.setPrice(order.getPrice()+ 1);
				}
				
				//orderDao.updateOrderOfGroup(order.getOrderID(), isoName);
				//System.out.println("执行的次数" + count++);
			}
		}
		
		allOrdersOfGroupService.updateSpecalRems(orders);
		System.out.println("ok");
		return true;
	}
	
	
	/**
	 * 初始化GameGroupMember中所有用户的UserUnique和currentPeriod
	 * @param groupName
	 * @return
	 */
	public boolean updateUserUnqiqueAndCurrentPeriod(String groupName) {
		try {
			gameGroupMemberService.updateUserUnqiqueAndCurrentPeriod(groupName);
			gameGroupDao.updateGroupCurrentPeriod(groupName, 1);//初始化游戏组的周期为1
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
