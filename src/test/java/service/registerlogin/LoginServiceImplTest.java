package service.registerlogin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
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
 * Created by 杨青 on 2016/8/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LoginServiceImplTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private LoginService loginService;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testloginForword_NotRegist() throws Exception {
        String userId = "aaaaaaaaaaaa";
        String password = "147852";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为没注册的用户", ReturnStatus.ERROR, map.get("status"));
    }
    @Test
    public void testloginForword_Register() throws Exception {
        String userId = "manman";
        String password = "147852";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为仅注册的用户", ReturnStatus.REGISTERPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_WrongPassword() throws Exception{
        String userId = "321";
        String password = "147852";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为密码错误", ReturnStatus.ERROR, map.get("status"));
    }
    @Test
    public void testloginForword_HaveNotJoinGroup() throws Exception {
        String userId = "朝辞白帝彩云间";
        String password = "123";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为还没加入分组的用户", ReturnStatus.NOTINAGROUPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_GAMECREATORBUTNOTSTARTPAGE() throws Exception {
        String userId = "12";
        String password = "123456789";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为加入分组但还没开始比赛且为创建者的用户",
                ReturnStatus.GAMECREATORBUTNOTSTARTPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_JoinGroupButNotBegin() throws Exception {
        String userId = "3";
        String password = "123";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为加入分组但还没开始比赛的普通用户",
                ReturnStatus.JOINGROUPBUTNOTBEGINPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_GameRunning() throws Exception {
        String userId = "SHENHAN";
        String password = "123";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为正在游戏的用户", ReturnStatus.GAMERUNNINGPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_FinishedGame() throws Exception {
        String userId = "11";
        String password = "11";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为结束游戏的用户", ReturnStatus.GAMEFINISHEDPAGE, map.get("status"));
    }
    @Test
    public void testloginForword_BrokenUp() throws Exception {
        String userId = "777";
        String password = "777";

        Map map = loginService.loginForword(userId, password, request);

        Assert.assertSame("测试角色为游戏破产的用户", ReturnStatus.BROKENUPPAGE, map.get("status"));
    }

    @Test
    public void isExistsApprover() throws Exception {

    }

    @Test
    public void isExistsRegister() throws Exception {

    }

    @Test
    public void findRegisterByUserId() throws Exception {

    }

    @Test
    public void findApprovedUserByUserId() throws Exception {

    }

    @Test
    public void showMenuList() throws Exception {

    }

    @Test
    public void loginStatus() throws Exception {

    }

}