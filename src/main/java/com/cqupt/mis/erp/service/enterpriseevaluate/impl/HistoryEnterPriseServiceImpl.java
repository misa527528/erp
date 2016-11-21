package com.cqupt.mis.erp.service.enterpriseevaluate.impl;

import com.cqupt.mis.erp.manager.history.*;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.model.enterpriseevaluate.*;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.service.enterpriseevaluate.HistoryEnterPriseService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("historyEnterPriseService")
public class HistoryEnterPriseServiceImpl implements HistoryEnterPriseService{

    @Resource
    private GameGroupDao gameGroupDao;
	@Resource
	private HisGameGroupMemberDao hisGameGroupMemberDao;
	@Resource
	private HisBalanceSheetDao hisBalanceSheetDao;
    @Resource
    private HisGameGroupDao hisGameGroupDao;
    @Resource
    private HisAdOfUserDao hisAdOfUserDao;
    @Resource
    private HisOrdersOfUserDao hisOrdersOfUserDao;
    @Resource
    private HisAccountHeadDao hisAccountHeadDao;
    @Resource
    private HisProductLineDao hisProductLineDao;
	
	private Object[] String;
	@Resource
	private GameGroupService  gameGroupService;
	@Override
	public List<AdminIncomeBean> getUserUnique(String groupname, int currentperiod) {
		return hisGameGroupMemberDao.getUserUnique(groupname, currentperiod);
	}

	@Override
	public double CalOwnerBenifit(String userUnique, int period) {
		return hisBalanceSheetDao.CalOwnerBenifit(userUnique, period);
	}

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
		
		List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
		int periodOfYear = 0;
		if(members.size()>0){
			periodOfYear = gameGroupDao.findPeriodOfYear(members.get(0).getUserunique());
		}
		List<Object> list = new ArrayList<Object>();
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
	public List<Map<String, Object>> getUserIORatesOfAd(String groupName,
			String year_string) {
		int year = Integer.parseInt(year_string);
		List<Map<String, Object>> userIORates = new ArrayList<Map<String, Object>>(10);

		List<UserInputAndOutputOfAd> userIOs = this.getUserInputAndOutput(year, groupName);

		for (int i = 0; i < userIOs.size(); i++) {
			Map<String, Object> userRate = new HashMap<String, Object>(5);
			userRate.put("username", userIOs.get(i).getUserID());
			userRate.put("rate", userIOs.get(i).getRate());
			userIORates.add(userRate);
		}
		return userIORates;
	}

	@SuppressWarnings("null")
	@Override
	public List<MemberSaleOfMarket> getGeneralMarketShare(String groupName,String year_string) {
		int year = Integer.parseInt(year_string);
		String[] MarketNames = new String[5];
		MarketNames[0] = "本地市场";
		MarketNames[1] = "区域市场";
		MarketNames[2] = "国内市场";
		MarketNames[3] = "亚洲市场";
		MarketNames[4] = "国际市场";
		List<MemberSaleOfMarket> list = null;
		List<MemberSaleOfMarket> memberSales = new ArrayList<MemberSaleOfMarket>();
		for (int j = 0; j < MarketNames.length; j++) {
			list = this.getMemberSalesByMarket(groupName,MarketNames[j], year);
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
		List<MemberSaleOfProduct> list = new ArrayList<MemberSaleOfProduct>();
		List<MemberSaleOfProduct> memberSales = new ArrayList<MemberSaleOfProduct>();
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
		Member member = hisGameGroupMemberDao.getGroup_Member(userunique);
		List<MemberCost> memberCosts = new ArrayList<MemberCost>();
		memberCosts = this.getMemberCostsByFinishedYears(userunique);
		for (int j = 0; j < memberCosts.size(); j++) {
			memberCosts.get(j).setMember(member);
		}
		return memberCosts;
	}

	@Override
	public List<ProductProfit> getProductsProfit(String groupName, String year_string) {
		int year = Integer.parseInt(year_string);
		List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
		List<ProductProfit> productsProfits = new ArrayList<ProductProfit>();
		List<ProductProfit> list = new ArrayList<ProductProfit>();
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
        List<ProduceCapacity> proCapacities = new ArrayList<ProduceCapacity>();
        List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
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
		List<Object> list = new ArrayList<Object>();
		List<GameGroupInfo> groups = hisGameGroupDao.getGroupNames();
		for (int i = 0; i < groups.size(); i++) {
			List<Member> members = hisGameGroupMemberDao.getGroup_Members(groups.get(i).getGroupName());
			int year = groups.get(i).getYears();
			int periodOfYears = hisGameGroupDao.findHistoryGameGroupInfo(
                    groups.get(i).getGroupName()).getPeriodsOfOneYear();
			List<Object> list1 = new ArrayList<Object>();
			for (int j = 0; j < members.size(); j++) {
				list1.add(members.get(j));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupNames",groups.get(i).getGroupName());
			map.put("members", list1);
			map.put("year", year);
			map.put("periodOfYears", periodOfYears);
			list.add(map);
		}

		return list;
	}

    //以下的代码是原来DaoImpl类的方法
    private List<UserInputAndOutputOfAd> getUserInputAndOutput(int year,String groupName) {
        List<UserInputAndOutputOfAd> userIOs = hisGameGroupMemberDao.getGroupMembers(groupName);
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < userIOs.size(); i++) {
            userIOs.get(i).setInput(hisAdOfUserDao.getUserInput(userIOs.get(i).getUserunique(), firstPeriod));
            userIOs.get(i).setOutput(hisOrdersOfUserDao.getUserOutput(
                    userIOs.get(i).getUserunique(), firstPeriod,lastPeriod));
        }
        return userIOs;
    }

    private List<MemberSaleOfMarket> getMemberSalesByMarket(String groupName,String marketname, int year) {
        List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
        List<MemberSaleOfMarket> memberSales = new ArrayList<MemberSaleOfMarket>(members.size());
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberSaleOfMarket memberSale = new MemberSaleOfMarket();
            memberSale.setMember(members.get(i));
            memberSale.setMarketname(marketname);
            memberSale.setSale(hisOrdersOfUserDao.getMemberSaleByMarket
                    (memberSale.getMember().getUserunique(), marketname, firstPeriod, lastPeriod));
            memberSales.add(memberSale);
        }
        return memberSales;
    }

    private  List<MemberSaleOfProduct> getMemberSalesByProduct(String groupName,String productName, int year) {
        List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
        List<MemberSaleOfProduct> memberSales = new ArrayList<MemberSaleOfProduct>(members.size());
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberSaleOfProduct memberSale = new MemberSaleOfProduct();
            memberSale.setMember(members.get(i));
            memberSale.setProductName(productName);
            memberSale.setSale(hisOrdersOfUserDao.getMemberSaleByProduct(
                    memberSale.getMember().getUserunique(), productName, firstPeriod, lastPeriod));
            memberSales.add(memberSale);
        }

        return memberSales;
    }

    private List<MemberCost> getMembersCosts(String groupName, int year) {
        List<MemberCost> membersCosts = new ArrayList<MemberCost>(10);
        List<Member> members = hisGameGroupMemberDao.getGroup_Members(groupName);
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        for (int i = 0; i < members.size(); i++) {
            MemberCost memberCost = new MemberCost();
            memberCost.setMember(members.get(i));
            String userunique = memberCost.getMember().getUserunique();
            memberCost.setTotalSale(hisOrdersOfUserDao.getUserOutput(userunique, firstPeriod,lastPeriod));
            memberCost.setProductCost(this.getProductCost(userunique, firstPeriod,lastPeriod));
            memberCost.setAdCost(hisAdOfUserDao.getAdCost(userunique, firstPeriod));
            memberCost.setOperationCost(this.getOperationCost(userunique,firstPeriod, lastPeriod));
            memberCost.setManagementCost(this.getManagementCost(userunique,firstPeriod, lastPeriod));
            memberCost.setDepreciationCost(this.getDepreciationCost(userunique,firstPeriod, lastPeriod));
            memberCost.setInterestCost(this.getInterestCost(userunique, firstPeriod,lastPeriod));
            memberCost.setYear(year);
            membersCosts.add(memberCost);
        }
        return membersCosts;
    }

    private double getManagementCost(String userunique, int firstPeriod,
                                     int lastPeriod) {
        double mangementCost = 1 * 4;
        return mangementCost;
    }

    private double getOperationCost(String userunique, int firstPeriod,int lastPeriod) {
        double operationCost = 5;
        return operationCost;
    }

    private int getFinishedYears(String userunique) {
        int periodOfYears = hisGameGroupDao.findHistoryPeriodOfYear(userunique);
        int period = hisGameGroupMemberDao.findHistoryCurrentTime(userunique);
        return period/periodOfYears +1;
    }

    private double getProductCost(String userunique, int firstPeriod,int lastPeriod) {
        double productCost = 0;
        List<Products> Products = hisOrdersOfUserDao.getProducts(userunique, firstPeriod,lastPeriod);
        for (int i = 0; i < Products.size(); i++) {
            productCost += this.getCostOfOrder(Products.get(i).getProductname(),Products.get(i).getPnumber());
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

    private double getInterestCost(String userunique, int firstPeriod,
                                   int lastPeriod) {
        return hisAccountHeadDao.getItemCost(userunique, "%利息%", firstPeriod, lastPeriod);
    }

    private double getDepreciationCost(String userunique, int firstPeriod,
                                       int lastPeriod) {
        return hisAccountHeadDao.getItemCost(userunique, "%折旧%", firstPeriod, lastPeriod);
    }

    private List<MemberCost> getMemberCostsByFinishedYears(String userunique) {
        List<MemberCost> memberCosts = new ArrayList<MemberCost>();
        int FinishedYears = getFinishedYears(userunique);
        for (int i = 1; i <= FinishedYears; i++) {
            MemberCost memberCost = new MemberCost();
            int firstPeriod = (i - 1) * 4 + 1;
            int lastPeriod = firstPeriod + 3;
            memberCost.setTotalSale(hisOrdersOfUserDao.getUserOutput(userunique, firstPeriod,lastPeriod));
            memberCost.setProductCost(this.getProductCost(userunique, firstPeriod,lastPeriod));
            memberCost.setAdCost(hisAdOfUserDao.getAdCost(userunique, firstPeriod));
            memberCost.setOperationCost(this.getOperationCost(userunique,firstPeriod, lastPeriod));
            memberCost.setManagementCost(this.getManagementCost(userunique,firstPeriod, lastPeriod));
            memberCost.setDepreciationCost(this.getDepreciationCost(userunique,firstPeriod, lastPeriod));
            memberCost.setInterestCost(this.getInterestCost(userunique, firstPeriod,lastPeriod));
            memberCost.setYear(i);
            memberCosts.add(memberCost);
        }
        return memberCosts;
    }

    private ProductProfit getProductProfit(String userunique,String productName, int firstPeriod, int lastPeriod) {
        List<Products> products = hisOrdersOfUserDao.getProductsByProductName(
                userunique, productName,firstPeriod, lastPeriod);
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

    public List<ProductProfit> getProductsProfitByUserUniqueAndYear(String userunique, int year) {
        List<ProductProfit> productsProfits = new ArrayList<ProductProfit>();
        int firstPeriod = (year - 1) * 4 + 1;
        int lastPeriod = firstPeriod + 3;
        productsProfits.add(this.getProductProfit(userunique, "P1", firstPeriod,lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P2", firstPeriod,lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P3", firstPeriod,lastPeriod));
        productsProfits.add(this.getProductProfit(userunique, "P4", firstPeriod,lastPeriod));
        return productsProfits;
    }

    private double getMemberCapacity(String userunique) {
        double capacity = 0;
        List<ProduceCapacity> produceCapacity = hisProductLineDao.getProducePeriod(userunique);
        for (int i = 0; i < produceCapacity.size(); i++) {
            capacity += 1.0 / produceCapacity.get(i).getProducePeriod();
        }

        return capacity;
    }
}
