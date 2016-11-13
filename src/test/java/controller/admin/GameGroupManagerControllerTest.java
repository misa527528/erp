package controller.admin;

import com.cqupt.mis.erp.controller.admin.GameGroupManagerController;
import com.cqupt.mis.erp.model.ReturnStatus;
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
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GameGroupManagerControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private GameGroupManagerController gameGroupManagerController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void showGameGroups() throws Exception {
        /*Map map = gameGroupManagerController.showGameGroups(response);
        List<GameGroupInfo> gameGroups = (List<GameGroupInfo>) map.get("data");

        Assert.assertSame(62, gameGroups.size());*/
    }

    @Test
    public void deteleGameGroup() throws Exception {
        String groupName = "3sqe";

        /*Map map = gameGroupManagerController.deteleGameGroup(response, groupName);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void findGameGroupMemberStatusByGroupName() throws Exception {
        String groupName = "007";

        /*Map map = gameGroupManagerController.findGameGroupMemberStatusByGroupName(response, groupName);
        List<GameGroupMemberStatus> gameGroupMemberStatuss = (List<GameGroupMemberStatus>) map.get("data");

        Assert.assertSame(1, gameGroupMemberStatuss.size());*/
    }

    @Test
    public void endPlayGame() throws Exception {
        String userUnique = "汤田乐20151009083255";
        String groupName = "erp实验第二轮";

        /*Map map = gameGroupManagerController.endPlayGame(response, userUnique, groupName);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void endAdvertising() throws Exception {
        String userUnique = "";
    }
    @Test
    public void endAdvertising_NotEnoughMoney() throws Exception {
        String userUnique = "";
    }
    @Test
    public void endAdvertising_Error() throws Exception {
        String userUnique = "吴艳琼20151009083255";

        Map map = gameGroupManagerController.endAdvertising(response, userUnique);

        Assert.assertSame(ReturnStatus.ERROR, 0);
    }

    @Test
    public void endChooseOrder() throws Exception {

    }

    @Test
    public void forwarPeriod() throws Exception {

    }

    @Test
    public void changeToHistory() throws Exception {

    }

}