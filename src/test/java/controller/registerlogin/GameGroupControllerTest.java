package controller.registerlogin;

import com.cqupt.mis.erp.controller.registerlogin.GameGroupController;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
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
 * Created by 杨青 on 2016/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GameGroupControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private GameGroupController gameGroupController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    // FIXME
    @Test
    public void showGameGroupList() throws Exception {
        Map map = gameGroupController.showGameGroupList(request, response);

        Assert.assertNotNull(map.get("status"));
    }

    @Test
    public void isGameGroupExist(){
        String gameGroupName = "t1";

       /* Map map = gameGroupController.isGameGroupExist(gameGroupName, response);

        Assert.assertSame("测试目标为游戏组已经存在", ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void isGameGroupExist_Not(){
        String gameGroupName = "还没有过这个游戏组啊";

       /* Map map = gameGroupController.isGameGroupExist(gameGroupName, response);

        Assert.assertSame("测试目标为游戏组不存在", ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void addGameGroup() throws Exception {
        request.getSession().setAttribute("userId", "Leipeng");
        GameGroupInfo gameGroupInfo = new GameGroupInfo();
        gameGroupInfo.setGroupName("没有过这个groupName");
        gameGroupInfo.setUserNumbers(3);
        gameGroupInfo.setYears(3);
        gameGroupInfo.setPeriodsOfOneYear(3);

       /* Map map = gameGroupController.addGameGroup(gameGroupInfo, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void isGameGroupRunning_SUCCESS(){
        request.getSession().setAttribute("groupName", "sy");

        //Map map = gameGroupController.isGameGroupRunning(request, response);

      //  Assert.assertSame("测试目的为游戏已经在进行", ReturnStatus.SUCCESS, map.get("status"));
    }
    @Test
    public void isGameGroupRunning_ERROR_CurrentPeriodZero(){
        request.getSession().setAttribute("groupName", "test");
      //  Map map = gameGroupController.isGameGroupRunning(request, response);

       // Assert.assertSame("测试目的为游戏组的currentPeriod==0", ReturnStatus.ERROR, map.get("status"));
    }
    @Test
    public void isGameGroupRunning_NoGroupNameERROR(){
        //Map map = gameGroupController.isGameGroupRunning(request, response);

        //Assert.assertSame("测试目的为游戏还未进行并且session中没有groupName", ReturnStatus.ERROR, map.get("status"));
    }

    @Test
    public void getGameGroupMessage(){
        request.getSession().setAttribute("groupName", "test_quitgroup");

       /* Map map = gameGroupController.getGameGroupMessage(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void getGameGroupMessage_SessionNotGroupName(){
        request.getSession().setAttribute("userId", "168");

       /* Map map = gameGroupController.getGameGroupMessage(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void deleteGameGroup(){
        request.getSession().setAttribute("groupName", "千万");

      /*  Map map = gameGroupController.deleteGameGroup(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void deleteGameGroup_DeleteErorr(){
        request.getSession().setAttribute("groupName", "fasdfasdgdafhgsd");

       /* Map map = gameGroupController.deleteGameGroup(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void deleteGameGroup_SessionNoGroupName(){
       // Map map = gameGroupController.deleteGameGroup(request, response);

       // Assert.assertSame(ReturnStatus.ERROR, map.get("status"));
    }

}