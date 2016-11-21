package com.cqupt.mis.erp.service.advertisement.impl;

import com.cqupt.mis.erp.manager.advertisement.AdOfUserDao;
import com.cqupt.mis.erp.manager.advertisement.AdStatusOfUserDao;
import com.cqupt.mis.erp.manager.finance.CashSheetDao;
import com.cqupt.mis.erp.manager.market.MarketBasicDao;
import com.cqupt.mis.erp.manager.order.ChooseOrderDao;
import com.cqupt.mis.erp.manager.order.OrdersOfUserDao;
import com.cqupt.mis.erp.manager.product.ProductBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.advertisement.Advertisement;
import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.order.OrderSequence;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.utils.ERPUtils;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("advertisementService")
public final class AdvertisementServiceImpl implements AdvertisementService {
    @Resource
    private AdOfUserDao adOfUserDao;
    @Resource
    private AdStatusOfUserDao adStatusOfUserDao;
    @Resource
    private CashSheetDao cashSheetDao;
    @Resource
    private GameGroupMemberDao gameGroupMemberDao;
    @Resource
    private MarketBasicDao marketBasicDao;
    @Resource
    private ProductBasicDao productBasicDao;
    @Resource
    private GameGroupDao gameGroupDao;
    @Resource
    private OrdersOfUserDao orderOfUserDao;
    @Resource
    private ChooseOrderDao chooseOrderDao;
    @Resource
    private AccountHeadService accountHeadService;
    @Resource
    private AccountDetailService accountDetailService;

    @Override
    public List<Advertisement> findAdvertisementByMarketName(String marketName, String userUnique) {
        return adOfUserDao.findAdvertisementByMarketName(marketName, userUnique);
    }

    @Override
    public List<Advertisement> findAlreadyAdvertisement(String userUnique) {
        return adOfUserDao.findAlreadAdvertisement(userUnique);
    }

    @Override
    public boolean updateAdvertisementForProduct(int advertisementId, double money) {
        // TODO: 2016/9/14  这里更新之前最好在看看这个市场开拓了没有? 后台校验一下.以防万一.虽然前台是不会展现这部分的信息的说.
        int result = adOfUserDao.updateAdvertisementForProduct(advertisementId, money);
        return result > 0;
    }


    @Override
    public int updateAdvertisementFinish(String userUnique) {
        Integer currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
        Integer currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
        Integer finishFlag = adStatusOfUserDao.findFinishAdFlag(userUnique, currentTime);

        if (finishFlag == 1) {
            //你已经进行过结束广告费投放操作
            return 3;
        } else {
            //如果没有完成投放广告就开始.
            Double summaryMoneyOfAD = adOfUserDao.findSummaryMoney(userUnique, currentPeriod);
            Double totalCash = cashSheetDao.findCash(userUnique);

            if (totalCash < summaryMoneyOfAD && totalCash > 0) {
                //2015.10.07 如果现金少于0，已经不要命了。那就无所谓了。随意点就ok 了。
                //如果现钱太少的话就要直接退出.如果不是的话就直接继续投放.
                //"结束广告费投入操作不成功！当前投入的广告费超出了企业所拥有的现金，请降低广告费的投放或者贷款获得更多的现金！";
                return 1;
            } else {
                //(步骤1).产生相应会计分录
                accountHeadService.addAccountHead(userUnique, currentPeriod, "投广告费");
                accountDetailService.addAccountDetail(userUnique, "营业费用", "现金", "借", "贷", summaryMoneyOfAD, summaryMoneyOfAD, (-summaryMoneyOfAD));

                //2015.1.16 王永何海源 联合对比调试
                //相对于旧版 少了减少现金的操作. 这里将补回来
                cashSheetDao.subtractCash(userUnique, ERPUtils.round(summaryMoneyOfAD));

                //(步骤2)更新表adstatusofuser的字段finishadflag设置为 1  先将自己的状态量变成了 1
                adStatusOfUserDao.updateFinishAdFlag(userUnique, currentPeriod, 1);

                //(步骤3)检查组内其它成员的投广告费状态
                int result = isCheckOtherMemberAdvertisementFinishFlag(userUnique, currentPeriod);

                if (result == 3) {
                    //这里要通知所有的同学要跳转到下一个页面 开始选单的页面.
                    //System.out.println("跳转选单的页面");
                    forwardToChooseOrderPage(userUnique);
                } else {
                    //如果不是通知所有人 那就直接通知 等待大厅的人.说我这里已经投放广告完成了.
                    //System.out.println("跳转进入等待大厅的页面");
                    personChange(userUnique);
                }

                return 2;
            }
        }
    }

    /**
     * forwardToChooseOrderPage 通知所有用户直接跳转到选单页面
     *
     * @param userUnique void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    private void forwardToChooseOrderPage(String userUnique) {
        String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
        DWRPush.pushMessageWithFilter("forwardToChooseOrder", null, "adAndOrderFlag" + groupName);
    }

    /**
     * personChange 通知其他在等待大厅的用户要刷新了.!
     *
     * @param userUnique void
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    private void personChange(String userUnique) {
        String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
        DWRPush.pushMessageWithFilter("personChange", null, "adAndOrderFlag" + groupName);
    }


    private int isCheckOtherMemberAdvertisementFinishFlag(String userUnique, Integer period) {

        //获取还没有破产的成员  这里的1 代表是  用户的status
        final Integer userStatus = 1;
        List<GameGroupMemberInfo> gameGroupList = gameGroupMemberDao.findGameGroupMemberListByStatus(userStatus, userUnique);

        String[] userUniques = new String[gameGroupList.size()];

        for (int i = 0; i < gameGroupList.size(); i++) {
            GameGroupMemberInfo gameGroupMember = gameGroupList.get(i);
            userUniques[i] = gameGroupMember.getUserUnique();
        }

        List<AdvertisementStatusOfUser> advertisementStatusOfUsers = adStatusOfUserDao.findAdvertisementStatusOfUserByUserUniques(userUniques, period);

        if (gameGroupList.size() == advertisementStatusOfUsers.size()) {
            for (AdvertisementStatusOfUser advertisementStatusOfUser : advertisementStatusOfUsers) {
                if (advertisementStatusOfUser.getFinishAdvertiseFlag() == 0) {
                    //因为其他成员没有完成投放广告的操作所有不能开始选单操作.
                    return 1;
                }
            }
        } else {
            //可能有些大伙已经超级快的开始一年的广告投放了,但是又一些人还是没有的说

            //"成功结束广告费投放，但由于组内其它成员未达到你当前年份，所以你还不能进入选单操作";
            return 2;
        }


        //更新所有的用户变成选单状态. 这里更新了状态之后应该要用dwr推送给所用用户准备跳转页面的说.!
        adStatusOfUserDao.updateChooseOrderFlag(userUniques, period, 1);

        List<String> marketNames = marketBasicDao.findMarketName();

        List<String> productNames = productBasicDao.findAllProductName();

        GameGroupInfo gameGroupInfo = gameGroupDao.findGameGroupInfoByUserUnique(userUnique);
        String groupName = gameGroupInfo.getGroupName();
        Integer end = gameGroupInfo.getCurrentPeriod() - 1;
        Integer begin = gameGroupInfo.getCurrentPeriod() - gameGroupInfo.getPeriodsOfOneYear();

        for (String marketName : marketNames) {
            for (String productName : productNames) {
                List<OrderSequence> orderSequencesA = orderOfUserDao.findOrdersMoneyOfUser(marketName, productName, begin, end, groupName);
                List<OrderSequence> orderSequencesB = orderOfUserDao.findUnOrdersMoneyOfUser(marketName, productName, begin, end, groupName);

                List<OrderSequence> moneySequence = countABMoney(gameGroupList, orderSequencesA, orderSequencesB);

                //对所有用户的A-B值进行排名。第1名为1分，2名为2分
                this.sortedListByMoneyValueDESC(moneySequence);
                //记分
                if (period == 1) {
                    this.countListValue(moneySequence, 0);
                } else {
                    this.countListValue(moneySequence, 1);
                }

                //对投入广告费的多少进行排名
                List<OrderSequence> adSequence = adOfUserDao.
                        findAdvertisementMoneyOfUsers(groupName, period, marketName, productName);
                this.sortedListByMoneyValueDESC(adSequence);

                //广告费的排名打分，第1名2分，2名4分，3名6分
                this.countListValue(adSequence, 2);

                //计算用户总平均分
                List<OrderSequence> orderSequence = this.countAverageValue(adSequence,
                        moneySequence, period, marketName, productName);

                //总平均分升序排列
                this.sortedListByMoneyValueASC(orderSequence);

                //设置选单顺序
                this.sortedOrderList(orderSequence);

                //将综合排名的结果存储为CHOOSEORDER表中的ChooseValue字段值。
                this.saveChooseOrders(orderSequence, groupName, marketName, productName, period);
            }
        }

        //有人想结束投放订单的 就是连 广告都不想投的同学直接就结束了(感觉好有问题....)
        for (GameGroupMemberInfo gameGroupMember : gameGroupList) {
            Integer finishOrderFlag = adStatusOfUserDao.findAdvertisementStatusOfUserByUserUnique(gameGroupMember.getUserUnique(), period).getFinishOrderFlag();
            if (finishOrderFlag == 1) {
                //有人已经结束了投放订单了.但是这里明明是投放广告费啊!为毛直接就投完订单捏???
                chooseOrderDao.updateChooseOrderValue(gameGroupMember.getUserUnique(), period, 10000, "%", "%");
            }
        }


        return 3;
    }


    private List<OrderSequence> countABMoney(List<GameGroupMemberInfo> members,
                                             List<OrderSequence> list1,
                                             List<OrderSequence> list2) {

        //System.out.println("进入计算所有用户的A-B值的 列表长度：" + );
        //循环 members 出来 然后 相应的用户相减 money值. 最后得到一个orderSequence
        List<OrderSequence> list = new ArrayList<OrderSequence>();
        for (int i = 0; i < members.size(); i++) {
            OrderSequence order = new OrderSequence();
            order.setUserUnique(members.get(i).getUserUnique());
            order.setMoney(0.0);
            for (int j = 0; j < list1.size(); j++) {
                if (members.get(i).getUserUnique().equals(list1.get(j).getUserUnique())) {
                    order.setMoney(list1.get(j).getMoney());
                }
            }

            for (int j = 0; j < list2.size(); j++) {
                if (members.get(i).getUserID().equals(list2.get(j).getUserUnique())) {
                    order.setMoney(order.getMoney() - list2.get(j).getMoney());
                }
            }
            list.add(order);
        }
        return list;
    }


    //对List排序，(降序)(按照OrderSequence中的Money属性排序)
    private void sortedListByMoneyValueDESC(List<OrderSequence> list) {

		/*System.out.println("需要进行降序排列的列表长度：---" + list.size());

		for(int i = 0; i < list.size(); i++){
			System.out.println("*kkkkkkkkkkkkkkkkkk排列前的顺序money:" + list.get(i).getMoney() + "--userUnique:" + list.get(i).getUserUnique());
		}*/

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                OrderSequence order1 = list.get(j);
                OrderSequence order2 = list.get(j + 1);
                if (order1.getMoney() < order2.getMoney()) {
                    OrderSequence temp = order1;
                    list.set(j, order2);
                    list.set(j + 1, temp);
                }
            }
        }

		/*for(int i = 0; i < list.size(); i++){
            System.out.println("*************************排列后的广告费:" + list.get(i).getMoney() + "--userUnique:" + list.get(i).getUserUnique());
		}*/

    }

    //对List排序，(升序)(按照OrderSequence中的Money属性排序)
    private void sortedListByMoneyValueASC(List<OrderSequence> list) {

        //System.out.println("需要进行降序排列的列表长度：---" + list.size());

		/*for(int i = 0; i < list.size(); i++){
            System.out.println("*kkkkkkkkkkkkkkkkkk升序排列前的顺序money:" + list.get(i).getMoney() + "--userUnique:" + list.get(i).getUserUnique());
		}
		*/
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                OrderSequence order1 = list.get(j);
                OrderSequence order2 = list.get(j + 1);
                if (order1.getMoney() > order2.getMoney()) {
                    OrderSequence temp = order1;
                    list.set(j, order2);
                    list.set(j + 1, temp);
                }
            }
        }

		/*for(int i = 0; i < list.size(); i++){
			System.out.println("*************************升序排列后的顺序money:" + list.get(i).getMoney() + "--userUnique:" + list.get(i).getUserUnique());
		}*/

    }

    //对顺序表List进行计分（OrderSequence中的sequence）
    private void countListValue(List<OrderSequence> list, Integer score) {
        for (int i = 0; i < list.size(); i++) {

            //System.out.println(list.get(i).getUserUnique() + "----用户投广告费:----" + list.get(i).getMoney());

            list.get(i).setMoney(score * (i + 1) + 0.0);

            //System.out.println(list.get(i).getUserUnique() + "----用户投广告费的得分:----" + list.get(i).getMoney());

        }
    }

    //设置选单顺序
    private void sortedOrderList(List<OrderSequence> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSequence(i + 1);
        }
    }


    //计算用户总平均分,
    private List<OrderSequence> countAverageValue(List<OrderSequence> adSequence,
                                                  List<OrderSequence> moneySequence,
                                                  Integer period,
                                                  String marketName,
                                                  String productName) {
        List<OrderSequence> list = new ArrayList<OrderSequence>();

        //System.out.println("计算用户总平均分方法内：订单金额列表长度--" + moneySequence.size() + "----广告费金额列表长度:" + adSequence.size());

        for (int i = 0; i < adSequence.size(); i++) {

            //如果广告费为0，将该用户从综合排名中移除
            Double adMoney = adOfUserDao.findUserAdvertisementMoney
                    (adSequence.get(i).getUserUnique(), period, marketName, productName);

            if (adMoney != 0.0) {
                OrderSequence order = new OrderSequence();
                order.setUserUnique(adSequence.get(i).getUserUnique());
                order.setMoney(adSequence.get(i).getMoney());
                for (int j = 0; j < moneySequence.size(); j++) {
                    if (order.getUserUnique().equals(moneySequence.get(j).getUserUnique())) {
                        order.setMoney((order.getMoney() + moneySequence.get(j).getMoney()) / 2);
                    }
                }
                list.add(order);
            }
        }
        return list;
    }

    //将某组在某季度某市场某产品的订单 保存到 选单顺序表中
    private void saveChooseOrders(List<OrderSequence> orderSequence,
                                  String groupName, String marketName,
                                  String productName, Integer period) {
        for (int i = 0; i < orderSequence.size(); i++) {
            chooseOrderDao.addChooseOrder(orderSequence.get(i).getUserUnique(),
                    period, marketName, productName, groupName,
                    orderSequence.get(i).getSequence());
        }
    }


    @Override
    public void initAdOfUser(String userUnique) {
        //产品名和市场名的集合，暂且放在MPs集合中！
        List<Advertisement> list = new ArrayList<Advertisement>();
        list = adOfUserDao.findMarketnameAndProductname();
        int period = gameGroupMemberDao.findCurrentTime(userUnique);

        for (int i = 0; i < list.size(); i++) {
            Advertisement ad = list.get(i);
            ad.setPeriod(period);
            ad.setUserUnique(userUnique);
            ad.setMoney((double) 0);
        }
        System.out.println(list.size());
	/*	Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", MPs);*/
        adOfUserDao.addAdvertisementList(list);
    }

    @Override
    public List<String> findProductNameByMarket(String userUnique, String marketName) {
        return adOfUserDao.findProductNameByMarket(userUnique, marketName);
    }

    @Override
    public List<String> findMoneyIntoMarket(String userUnique) {
        return adOfUserDao.findMoneyIntoMarket(userUnique);
    }


}
