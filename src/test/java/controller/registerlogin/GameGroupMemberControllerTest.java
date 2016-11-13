package controller.registerlogin;

import com.cqupt.mis.erp.controller.registerlogin.GameGroupMemberController;
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
 * Created by 杨青 on 2016/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GameGroupMemberControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private GameGroupMemberController gameGroupMemberController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void joinGroup() throws Exception {
        request.getSession().setAttribute("userId", "dd");
        String gameGroupName = "test";

        /*Map map = gameGroupMemberController.joinGroup(gameGroupName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void joinGroup_SessionNoUserId() throws Exception {
        String gameGroupName = "test";

      /*  Map map = gameGroupMemberController.joinGroup(gameGroupName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void joinGroup_HavenJoinedGroup() throws Exception {
        request.getSession().setAttribute("userId", "3");
        String gameGroupName = "test_quitgroup";

       /* Map map = gameGroupMemberController.joinGroup(gameGroupName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void exitGame(){
        request.getSession().setAttribute("groupName", "hala");
        request.getSession().setAttribute("userUnique", "caoyang147071223586965");

      /*  Map map = gameGroupMemberController.exitGame(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void exitGroup(){
        request.getSession().setAttribute("userId","李琴");
        request.getSession().setAttribute("groupName", "duguqiubai");

        Map map = gameGroupMemberController.exitGroup(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));
    }
    @Test
    public void exitGroup_NoUserId(){
        request.getSession().setAttribute("groupName", "duguqiubai");
        Map map = gameGroupMemberController.exitGroup(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));
    }
    @Test
    public void exitGroup_NoGroupName(){
        request.getSession().setAttribute("userId","李琴");
        Map map = gameGroupMemberController.exitGroup(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));
    }

    @Test
    public void showGameGroupMemberListAJAX(){
        String groupName = "4test";
        request.getSession().setAttribute("groupName", groupName);

       /* Map map = gameGroupMemberController.showGameGroupMemberListAJAX(request, response);
        List<GameGroupMemberInfo> groupMembers = (List<GameGroupMemberInfo>) map.get("data");
        Assert.assertSame(1, groupMembers.size());*/
    }
}