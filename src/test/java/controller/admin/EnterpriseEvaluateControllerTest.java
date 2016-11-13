package controller.admin;

import com.cqupt.mis.erp.controller.admin.EnterpriseEvaluateController;
import com.cqupt.mis.erp.model.enterpriseevaluate.MemberCost;
import org.junit.Assert;
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
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EnterpriseEvaluateControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private EnterpriseEvaluateController enterpriseEvaluateController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void showGroupMembers() throws Exception {
        /*Map map = enterpriseEvaluateController.showGroupMembers(response);
        List<Object> list = (List<Object>) map.get("data");

        Assert.assertSame(56, list.size());*/
    }

    @Test
    public void showEndValue() throws Exception {
        String year = "3";
        String gameGroupName = "duguqiubai";
        String userUnique = "lixu20151009091538";

       /* Map map = enterpriseEvaluateController.showEndValue(response, year, gameGroupName, userUnique);
        List<Object> endValues = (List<Object>) map.get("data");

        Assert.assertSame(4, endValues.size());*/
    }

    @Test
    public void showEndValues() throws Exception {
        String groupName = "007";
        String year = "1";
        String period = "2";

       /* Map map = enterpriseEvaluateController.showEndValues(response, year, groupName, period);
        List<Object> endValues = (List<Object>) map.get("data");

        Assert.assertSame(1, endValues.size());*/
    }

    @Test
    public void getUserIORatesOfAd() throws Exception {
        String groupName = "007";
        String year = "5";

        /*Map map = enterpriseEvaluateController.getUserIORatesOfAd(groupName, year, response);
        List<Map<String, Object>> userIORates = (List<Map<String,Object>>) map.get("data");

        Assert.assertSame(1, userIORates.size());*/
    }

    @Test
    public void getGeneralMarketShare() throws Exception {
        String groupName = "007";
        String year = "5";

       /* Map map = enterpriseEvaluateController.getGeneralMarketShare(groupName, year, response);
        List<MemberSaleOfProduct> memberSaleOfProducts = (List<MemberSaleOfProduct>) map.get("data");

        Assert.assertSame(5, memberSaleOfProducts.size());*/
    }

    @Test
    public void getProductMarketShare() throws Exception {
        String groupName = "007";
        String year = "5";

        /*Map map = enterpriseEvaluateController.getProductMarketShare(groupName, year, response);
        List<MemberSaleOfProduct> memberSaleOfProducts = (List<MemberSaleOfProduct>) map.get("data");

        Assert.assertSame(4, memberSaleOfProducts.size());*/
    }

    @Test
    public void getCostStructure() throws Exception {
        String groupName = "007";
        String year = "5";

        /*Map map = enterpriseEvaluateController.getCostStructure(groupName, year, response);
        List<MemberCost> memberCosts = (List<MemberCost>) map.get("data");

        Assert.assertSame(1,memberCosts.size());*/
    }

    @Test
    public void getCostStructureChanges() throws Exception {
        String groupName = "ggmu";
        String userunique = "周子渝20151008100727";

        Map map = enterpriseEvaluateController.getCostStructureChanges(groupName, userunique, response);
        List<MemberCost> memberCosts = (List<MemberCost>) map.get("data");

        Assert.assertSame(1, memberCosts.size());
    }

    @Test
    public void getProductsProfit() throws Exception {
        String groupName = "007";
        String year = "5";

       /* Map map = enterpriseEvaluateController.getProductsProfit(groupName, year, response);
        List<ProductProfit> productProfits = (List<ProductProfit>) map.get("data");

        Assert.assertSame(4, productProfits.size());*/
    }

    @Test
    public void getMembersCapacity() throws Exception {
        String groupName = "007";

        /*Map map = enterpriseEvaluateController.getMembersCapacity(groupName, response);
        List<ProduceCapacity> produceCapacities = (List<ProduceCapacity>) map.get("data");

        Assert.assertSame(1, produceCapacities.size());*/
    }

}