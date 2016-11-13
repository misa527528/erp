package controller.admin;

import com.cqupt.mis.erp.controller.admin.HistoryEnterpriseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class HistoryEnterpriseControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private HistoryEnterpriseController historyEnterpriseController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void showGroupMembers() throws Exception {
        /*Map map = historyEnterpriseController.showGroupMembers(response);
        List<Object> list = (List<Object>) map.get("data");

        Assert.assertSame(10, list.size());*/
    }

    @Test
    public void showEndValue() throws Exception {
        String year = "4";
        String groupName = "007---2016年09月19";
        String userunique = "汤田乐20151008100727";

        /*Map map = historyEnterpriseController.showEndValue(response, year, groupName, userunique);
        List<Object> endValues = (List<Object>) map.get("data");

        Assert.assertSame(4, endValues.size());*/
    }

    @Test
    public void showEndValues() throws Exception {
        String year = "4";
        String groupName = "erp实验课一---2002年08月07";
        String period = "3";

       /* Map map = historyEnterpriseController.showEndValues(response, year, groupName, period);
        List<Object> endValues = (List<Object>) map.get("data");

        Assert.assertSame(9, endValues.size());*/
    }

    @Test
    public void getUserIORatesOfAd() throws Exception {
        String year = "1";
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getUserIORatesOfAd(response, year, groupName);
        List<Map<String, Object>> userIORates = ( List<Map<String, Object>>) map.get("data");

        Assert.assertSame(1, userIORates.size());*/
    }

    @Test
    public void getGeneralMarketShare() throws Exception {
        String year = "1";
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getGeneralMarketShare(response, year, groupName);
        List<MemberSaleOfMarket> memberSaleOfMarkets = (List<MemberSaleOfMarket>) map.get("data");

        Assert.assertSame(5, memberSaleOfMarkets.size());*/
    }

    @Test
    public void getProductMarketShare() throws Exception {
        String year = "1";
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getProductMarketShare(response, year, groupName);
        List<MemberSaleOfProduct> memberSaleOfProducts = (List<MemberSaleOfProduct>) map.get("data");

        Assert.assertSame(4, memberSaleOfProducts.size());*/
    }

    @Test
    public void getCostStructure() throws Exception {
        String year = "1";
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getCostStructure(response, year, groupName);
        List<MemberCost> memberCosts = (List<MemberCost>) map.get("data");

        Assert.assertSame(1, memberCosts.size());*/
    }

    @Test
    public void getCostStructureChanges() throws Exception {
        String userunique = "lixu20151009095411";
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getCostStructureChanges(response, userunique, groupName);
        List<MemberCost> memberCosts = (List<MemberCost>) map.get("data");

        Assert.assertSame(1, memberCosts.size());*/
    }

    @Test
    public void getProductsProfit() throws Exception {
        String year = "3";
        String groupName = "007---2016年09月19";

        /*Map map  = historyEnterpriseController.getProductsProfit(response, year, groupName);
        List<ProductProfit> productProfits = (List<ProductProfit>) map.get("data");

        Assert.assertSame(4, productProfits.size());*/
    }

    @Test
    public void getMembersCapacity() throws Exception {
        String groupName = "007---2016年09月19";

        /*Map map = historyEnterpriseController.getMembersCapacity(response, groupName);
        List<ProduceCapacity> produceCapacities = (List<ProduceCapacity>) map.get("data");

        Assert.assertSame(1, produceCapacities.size());*/
    }

}