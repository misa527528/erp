package com.cqupt.mis.erp.manager.enterpriseevaluate;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.enterpriseevaluate.*;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("historyEnterPriseDao")
public interface HistoryEnterPriseDao extends BaseDao {
    /**
     * 计算所有者权益合计
     *
     * @param userUnique
     * @param period
     * @return
     */
    public double CalOwnerBenifit(String userUnique, int period);

    public List<AdminIncomeBean> getUserUnique(String groupname, int currentperiod);

    public List<UserInputAndOutputOfAd> getUserInputAndOutput(int year, String groupName);

    public List<MemberSaleOfMarket> getMemberSalesByMarket(String groupName, String string, int year);

    public List<MemberSaleOfProduct> getMemberSalesByProduct(String groupName, String string, int year);

    public List<MemberCost> getMembersCosts(String groupName, int year);

    public List<Member> getGroup_Members(String groupName);

    public List<MemberCost> getMemberCostsByFinishedYears(String userunique);

    public List<ProductProfit> getProductsProfit(String userunique, int year);

    public List<ProduceCapacity> getMembersCapacity(String groupName);

    public List<GameGroupInfo> getGroupNames();

    public Member getGroup_Member(String userunique);
}
