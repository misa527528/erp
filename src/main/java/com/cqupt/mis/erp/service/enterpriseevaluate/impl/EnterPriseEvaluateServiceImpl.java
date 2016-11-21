package com.cqupt.mis.erp.service.enterpriseevaluate.impl;

import com.cqupt.mis.erp.manager.advertisement.AdOfUserDao;
import com.cqupt.mis.erp.manager.factory.ProductLineDao;
import com.cqupt.mis.erp.manager.finance.AccountHeadDao;
import com.cqupt.mis.erp.manager.finance.BalanceSheetDao;
import com.cqupt.mis.erp.manager.order.OrdersOfUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.enterpriseevaluate.*;
import com.cqupt.mis.erp.service.enterpriseevaluate.EnterPriseEvaluateService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zjs
 * 
 * 
 */
@Service("enterPriseEvaluateService")
public class EnterPriseEvaluateServiceImpl implements EnterPriseEvaluateService {

	@Resource
	private BalanceSheetDao balanceSheetDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
    @Resource
    private GameGroupDao gameGroupDao;
    @Resource
    private AdOfUserDao adOfUserDao;
    @Resource
    private OrdersOfUserDao ordersOfUserDao;
    @Resource
    private AccountHeadDao accountHeadDao;
    @Resource
    private ProductLineDao prductProductLineDao;

	private Object[] String;
	@Resource
	private GameGroupService  gameGroupService;

	@Override
	public List<AdminIncomeBean> getUserUnique(String groupname,
			int currentperiod) {
		return gameGroupMemberDao.getUserUnique(groupname, currentperiod);
	}

	@Override
	public double CalOwnerBenifit(String userUnique, int period) {
		return balanceSheetDao.CalOwnerBenifit(userUnique, period);
	}

	/**
	 * @Override public double[] showEndValue(String year, String groupName) {
	 *           int yearint = Integer.parseInt(year); double[] endValues = new
	 *           double[4]; double sumEndValue = 0; if (yearint == 1) { for (int
	 *           currentperiod = 1; currentperiod < 5; currentperiod++) {
	 *           List<AdminIncomeBean> list = getUserUnique(groupName,
	 *           currentperiod); for (int i = 0; i < list.size(); i++) { double
	 *           endValue = CalOwnerBenifit(list.get(i).getUserUnique(),
	 *           currentperiod); sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-1] = sumEndValue; } } else if (yearint
	 *           == 2) { for (int currentperiod = 5; currentperiod < 9;
	 *           currentperiod++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-5] = sumEndValue; } } else if (yearint
	 *           == 3) { for (int currentperiod = 9; currentperiod < 13;
	 *           currentperiod++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-9] = sumEndValue; } } else if (yearint
	 *           == 4) { for (int currentperiod = 13; currentperiod < 17;
	 *           currentperiod++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-13] = sumEndValue; } } else if (yearint
	 *           == 5) { for (int currentperiod = 17; currentperiod < 21;
	 *           currentperiod++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-17] = sumEndValue; } } else if (yearint
	 *           == 6) { for (int currentperiod = 21; currentperiod < 25;
	 *           currentperiod++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; }
	 *           endValues[currentperiod-21] = sumEndValue; } } return
	 *           endValues; }
	 */
	/**
	 * @Override public double[] showEndValue(String year, String groupName) {
	 *           int yearint = Integer.parseInt(year); double[] endValues = new
	 *           double[4]; double sumEndValue = 0; int currentperiodStart =
	 *           (yearint - 1) * 4 + 1; for (int currentperiod =
	 *           currentperiodStart, j = 0; currentperiod < currentperiodStart +
	 *           4; currentperiod++, j++) { List<AdminIncomeBean> list =
	 *           getUserUnique(groupName, currentperiod); for (int i = 0; i <
	 *           list.size(); i++) { double endValue =
	 *           CalOwnerBenifit(list.get(i).getUserUnique(), currentperiod);
	 *           sumEndValue = sumEndValue + endValue; } endValues[j] =
	 *           sumEndValue; } return endValues; }
	 */
	@Override
	public List<Object> showEndValue(String year, String groupName,
			String userunique) {
		int yearint = Integer.parseInt(year);
		int currentperiodStart = (yearint - 1) * 4 + 1;
		List<Object> list = new ArrayList<Object>();
		for (int currentperiod = currentperiodStart; currentperiod < currentperiodStart + 4; currentperiod++) {
			AdminIncomeBean adminIncomeBean = new AdminIncomeBean();
			double endValue = CalOwnerBenifit(userunique, currentperiod);
			adminIncomeBean.setCurrentPeriod(currentperiod);
			adminIncomeBean.setOwnerBenifit(endValue);

			list.add(adminIncomeBean);
		}
		return list;
	}
	
	@Override
	public List<Object> showEndValues(String year, String period,String groupName) {
		int yearint = Integer.parseInt(year);
		int periodint = Integer.parseInt(period);
		
		List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
		int periodOfYear = 0;
		if(members.size()>0){
			periodOfYear = gameGroupDao.findPeriodOfYear(members.get(0).getUserunique());
		}
		List<Object> list = new ArrayList<>();
		for(int i=0;i<members.size();i++){
			AdminIncomeBean adminIncomeBean = new AdminIncomeBean();
			double endValue = CalOwnerBenifit(members.get(i).getUserunique(), (yearint-1)*periodOfYear+periodint);
			adminIncomeBean.setUserUnique(members.get(i).getUserunique());
			adminIncomeBean.setUserId(members.get(i).getUserID());
			adminIncomeBean.setCurrentPeriod(periodint);
			adminIncomeBean.setOwnerBenifit(endValue);
			list.add(adminIncomeBean);
		}
		return list;
	}
	
	

	@Override
	public List<Map<String, Object>> getUserIORatesOfAd(String groupName, String year_string) {
		int year = Integer.parseInt(year_string);
		List<Map<String, Object>> userIORates = new ArrayList<>(10);

        try {
			List<UserInputAndOutputOfAd> userIOs = this.getUserInputAndOutput(year, groupName);

			for (int i = 0; i < userIOs.size(); i++) {
				Map<String, Object> userRate = new HashMap<>(5);
				userRate.put("username", userIOs.get(i).getUserID());
				userRate.put("rate", userIOs.get(i).getRate());
				userIORates.add(userRate);
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return userIORates;
	}

	@SuppressWarnings("null")
	@Override
	public List<MemberSaleOfMarket> getGeneralMarketShare(String groupName,
			String year_string) {
		int year = Integer.parseInt(year_string);
		String[] MarketNames = new String[5];
		MarketNames[0] = "本地市场";
		MarketNames[1] = "区域市场";
		MarketNames[2] = "国内市场";
		MarketNames[3] = "亚洲市场";
		MarketNames[4] = "国际市场";
		List<MemberSaleOfMarket> list;
		List<MemberSaleOfMarket> memberSales = new ArrayList<>();
		for (int j = 0; j < MarketNames.length; j++) {
			list = this.getMemberSalesByMarket(groupName, MarketNames[j], year);
			for (int i = 0; i < list.size(); i++) {
				memberSales.add(list.get(i));
			}
		}
		return memberSales;
	}

	@Override
	public List<MemberSaleOfProduct> getProductMarketShare(String groupName,
			String year_string) {
		int year = Integer.parseInt(year_string);
		String[] productNames = new String[5];
		productNames[0] = "P1";
		productNames[1] = "P2";
		productNames[2] = "P3";
		productNames[3] = "P4";
		List<MemberSaleOfProduct> list;
		List<MemberSaleOfProduct> memberSales = new ArrayList<>();
		for (int j = 0; j < productNames.length - 1; j++) {
			list = this.getMemberSalesByProduct(groupName,productNames[j], year);
			for (int i = 0; i < list.size(); i++) {
				memberSales.add(list.get(i));
			}
		}
		return memberSales;
	}

	@Override
	public List<MemberCost> getCostStructure(String groupName,String year_string) {
		int year = Integer.parseInt(year_string);
		List<MemberCost> membersCosts = this.getMembersCosts(groupName, year);
		return membersCosts;
	}

	@Override
	public List<MemberCost> getCostStructureChanges(String groupName,String userunique) {
//		List<Member> members = enterPriseEvaluateDao.getGroup_Members(groupName);
		Member member = gameGroupMemberDao.getGroup_Member(userunique);
		List<MemberCost> memberCosts;
//		for (int i = 0; i < members.size(); i++) {
//			memberCosts = enterPriseEvaluateDao
//					.getMemberCostsByFinishedYears(members.get(i)
//							.getUserunique());
//			for (int j = 0; j < memberCosts.size(); j++) {
//				memberCosts.get(j).setMember(members.get(i));
//			}
//		}
		memberCosts = this.getMemberCostsByFinishedYears(userunique);
		for (int j = 0; j < memberCosts.size(); j++) {
			memberCosts.get(j).setMember(member);
		}
		return memberCosts;
	}

	@Override
	public List<ProductProfit> getProductsProfit(String groupName, String year_string) {
		int year = Integer.parseInt(year_string);
		List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
		List<ProductProfit> productsProfits;
		List<ProductProfit> list = new ArrayList<>();
		for (int i = 0; i < members.size(); i++) {
			productsProfits = this.getProductsProfitByUserUniqueAndYear(members.get(i).getUserunique(), year);
			for (int j = 0; j < productsProfits.size(); j++) {
				productsProfits.get(j).setMember(members.get(i));
				list.add(productsProfits.get(j));
			}
		}
		return list;

	}

	@Override
	public List<ProduceCapacity> getMembersCapacity(String groupName) {
        List<ProduceCapacity> proCapacities = new ArrayList<>();
        List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
        for (int i = 0; i < members.size(); i++) {
            ProduceCapacity proCapacity = new ProduceCapacity();
            proCapacity.setMember(members.get(i));
            proCapacity.setCapacity(this.getMemberCapacity(proCapacity.getMember().getUserunique()));
            proCapacities.add(proCapacity);
        }
        return proCapacities;
	}

	@Override
	public List<Object> showGroupMembers() {
		List<Object> list = new ArrayList<>();

		List<String> groupNames = gameGroupDao.getGroupNames();
		for (int i = 0; i < groupNames.size(); i++) {
			List<Member> members = gameGroupMemberDao.getGroup_Members(groupNames.get(i));
			int year = gameGroupService.findYearsByGroupName(groupNames.get(i));
			List<Object> list1 = new ArrayList<>();

			for (int j = 0; j < members.size(); j++) {
				list1.add(members.get(j));
			}

            int periodsOfOneYear = gameGroupDao.findPeriodOfYearByGroupName(groupNames.get(i));

			Map<String, Object> map = new HashMap();
			map.put("groupNames",groupNames.get(i));
			map.put("members", list1);
			map.put("year", year);
            map.put("periodsOfOneYear", periodsOfOneYear);
			list.add(map);
		}
		return list;
	}

    //一下的方法是老版本放在DaoImpl的，现在提到service层
    //后面迭代的时候要再重构一次，这样写导致该类代码量激增
    // TODO: 2016/8/25 添加测试，该方法原来是DaoImpl的 
    private List<MemberSaleOfMarket> getMemberSalesByMarket(String groupName, String marketname, int year){
        List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
        List<MemberSaleOfMarket> memberSales = new ArrayList<>(members.size());
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberSaleOfMarket memberSale = new MemberSaleOfMarket();
            memberSale.setMember(members.get(i));
            memberSale.setMarketname(marketname);
            memberSale.setSale(adOfUserDao.getMemberSaleByMarket(memberSale.getMember().getUserunique(), 
                    marketname, firstPeriod, lastPeriod));
            memberSales.add(memberSale);
        }
        return memberSales;
    }

    private List<MemberSaleOfProduct> getMemberSalesByProduct(String groupName, String productName, int year) {
        List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
        List<MemberSaleOfProduct> memberSales = new ArrayList<>(
                members.size());
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberSaleOfProduct memberSale = new MemberSaleOfProduct();
            memberSale.setMember(members.get(i));
            memberSale.setProductName(productName);
            memberSale.setSale(ordersOfUserDao.getMemberSaleByProduct(
                    memberSale.getMember().getUserunique(), productName, firstPeriod, lastPeriod));
            memberSales.add(memberSale);
        }
        return memberSales;
    }

    private List<MemberCost> getMembersCosts(String groupName, int year) {
        List<MemberCost> membersCosts = new ArrayList<>(10);
        List<Member> members = gameGroupMemberDao.getGroup_Members(groupName);
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberCost memberCost = new MemberCost();
            memberCost.setMember(members.get(i));
            String userunique = memberCost.getMember().getUserunique();
            memberCost.setTotalSale(ordersOfUserDao.getUserOutput(userunique, firstPeriod, lastPeriod));
            memberCost.setProductCost(getProductCost(userunique, firstPeriod, lastPeriod));
            memberCost.setAdCost(adOfUserDao.getAdCost(userunique, firstPeriod));
            memberCost.setOperationCost(this.getOperationCost(userunique, firstPeriod, lastPeriod));
            memberCost.setManagementCost(this.getManagementCost(userunique, firstPeriod, lastPeriod));
            memberCost.setDepreciationCost(this.getDepreciationCost(userunique, firstPeriod, lastPeriod));
            memberCost.setInterestCost(this.getInterestCost(userunique, firstPeriod, lastPeriod));
            memberCost.setYear(year);
            membersCosts.add(memberCost);
        }
        return membersCosts;
    }

    private double getProductCost(String userunique, int firstPeriod,
                                  int lastPeriod) {
        double productCost = 0;
        List<Products> Products = ordersOfUserDao.getProducts(userunique, firstPeriod,
                lastPeriod);
        for (int i = 0; i < Products.size(); i++) {
            productCost += getCostOfOrder(Products.get(i).getProductname(), Products.get(i).getPnumber());
        }

        return productCost;
    }

    private double getCostOfOrder(String productName, int pNumber) {
        double costOfOrder = 0;
        if (productName != null) {
            if (productName.equals("P1")) {
                costOfOrder = pNumber * (1 + 0 + 0 + 0);
            } else if (productName.equals("P2")) {
                costOfOrder = pNumber * (1 + 1 + 0 + 0);
            } else if (productName.equals("P3")) {
                costOfOrder = pNumber * (0 + 2 + 1 + 0);
            } else if (productName.equals("P4")) {
                costOfOrder = pNumber * (0 + 1 + 1 + 2);
            }
        }
        return costOfOrder;
    }

    private double getOperationCost(String userunique, int firstPeriod, int lastPeriod) {
        double operationCost = 5;
        return operationCost;
    }

    private double getManagementCost(String userunique, int firstPeriod, int lastPeriod) {
        double mangementCost = 1 * 4;
        return mangementCost;
    }

    private double getDepreciationCost(String userunique, int firstPeriod, int lastPeriod) {
        return accountHeadDao.getItemCost(userunique, "%折旧%", firstPeriod, lastPeriod);
    }

    private double getInterestCost(String userunique, int firstPeriod, int lastPeriod) {
        return accountHeadDao.getItemCost(userunique, "%利息%", firstPeriod, lastPeriod);
    }

    private List<ProductProfit> getProductsProfitByUserUniqueAndYear(String userunique, int year) {
        List<ProductProfit> productsProfits = new ArrayList<>();
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        productsProfits.add(this.getProductProfit(userunique, "P1", firstPeriod, lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P2", firstPeriod, lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P3", firstPeriod, lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P4", firstPeriod, lastPeriod));
        return productsProfits;
    }

    private ProductProfit getProductProfit(String userunique,
                                           String productName, int firstPeriod, int lastPeriod) {
        List<Products> products = ordersOfUserDao.getProductsByProductName(
                userunique, productName, firstPeriod, lastPeriod);
        ProductProfit productProfit = new ProductProfit();
        double cost = 0;
        double sale = 0;
        for (int i = 0; i < products.size(); i++) {
            cost += getCostOfOrder(productName, products.get(i).getPnumber());
            sale += products.get(i).getPnumber() * products.get(i).getPrice();
        }
        productProfit.setProductname(productName);
        productProfit.setCost(cost);
        productProfit.setSale(sale);
        return productProfit;
    }

    private List<MemberCost> getMemberCostsByFinishedYears(String userunique) {
        List<MemberCost> memberCosts = new ArrayList<>();
        int FinishedYears = this.getFinishedYears(userunique);
        for (int i = 1; i <= FinishedYears; i++) {
            MemberCost memberCost = new MemberCost();
            int firstPeriod = (i - 1) * 4 + 1;
            int lastPeriod = firstPeriod + 3;
            memberCost.setTotalSale(ordersOfUserDao.getUserOutput(userunique, firstPeriod,
                    lastPeriod));
            memberCost.setProductCost(this.getProductCost(userunique, firstPeriod,
                    lastPeriod));
            memberCost.setAdCost(adOfUserDao.getAdCost(userunique, firstPeriod));
            memberCost.setOperationCost(this.getOperationCost(userunique,
                    firstPeriod, lastPeriod));
            memberCost.setManagementCost(this.getManagementCost(userunique,
                    firstPeriod, lastPeriod));
            memberCost.setDepreciationCost(this.getDepreciationCost(userunique,
                    firstPeriod, lastPeriod));
            memberCost.setInterestCost(this.getInterestCost(userunique, firstPeriod,
                    lastPeriod));
            memberCost.setYear(i);
            memberCosts.add(memberCost);
        }
        return memberCosts;
    }

    private int getFinishedYears(String userunique) {
        int periodOfYears = gameGroupDao.findPeriodOfYear(userunique);
        int period = gameGroupMemberDao.findCurrentTime(userunique);
        return period/periodOfYears +1;
    }

    private double getMemberCapacity(String userunique) {
        double capacity = 0;
        List<ProduceCapacity> produceCapacity = prductProductLineDao.getProducePeriod(userunique);
        for (int i = 0; i < produceCapacity.size(); i++) {
            capacity += 1.0 / produceCapacity.get(i).getProducePeriod();
        }
        return capacity;
    }

    private List<UserInputAndOutputOfAd> getUserInputAndOutput(int year, String groupName){
        List<UserInputAndOutputOfAd> userIOs = gameGroupMemberDao.getGroupMembers(groupName);
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < userIOs.size(); i++) {
            userIOs.get(i).setInput(
                    adOfUserDao.getUserInput(userIOs.get(i).getUserunique(), firstPeriod));
            userIOs.get(i).setOutput(
                   ordersOfUserDao. getUserOutput(userIOs.get(i).getUserunique(), firstPeriod,
                            lastPeriod));
        }
        return userIOs;
    }
}
