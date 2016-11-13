package controller.admin;

import com.cqupt.mis.erp.controller.admin.UserManagerController;
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
 * Created by 杨青 on 2016/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserManagerControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private UserManagerController userManagerController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void findAllApprovedUser(){
        /*Map map = userManagerController.findAllApprovedUser(response);
        List<ApprovedUserInfo> approvedUserInfos = (List<ApprovedUserInfo>) map.get("data");

        Assert.assertSame(104, approvedUserInfos.size());*/
    }

    @Test
    public void findAllRegister(){
        /*Map map = userManagerController.findAllRegister(response);
        List<RegisterInfo> registerInfos = (List<RegisterInfo>) map.get("data");

        Assert.assertSame(1, registerInfos.size());*/
    }

    @Test
    public void updateApprovedUserInfo(){
        String userID = "2012211146";
        String name = "lalala";
        String className = "0311204";
        String major = "IMIS";
        String studentId = "2011204897";

        /*Map map = userManagerController.updateApprovedUserInfo(userID, name, className, major, studentId, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void updateApprovedUserInfo_Error(){
        String userID = "sdsdfa";
        String name = "lalala";
        String className = "0311204";
        String major = "IMIS";
        String studentId = "2011204897";

        /*Map map = userManagerController.updateApprovedUserInfo(userID, name, className, major, studentId, response);

        Assert.assertSame("无法找到该用户", map.get("message"));*/
    }

    @Test
    public void deleteApprUser() throws Exception {
        String userId = "321";

       /* Map map = userManagerController.deleteApprUser(userId, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void deleteApprUser_Error(){
        String userId = "asdk[opiuasdpofau";

        /*Map map = userManagerController.deleteApprUser(userId, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void deleteBatchApprUsers(){
        String[] userIds = {"sdfgsd", "cx"};

        /*Map map = userManagerController.deleteBatchApprUsers(userIds, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void deleteBatchRegiUsers(){
        String[] userIds = {"sdfgsd", "cx"};

        /*Map map = userManagerController.deleteBatchRegiUsers(userIds, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void deleteRegiUser(){
        String userId = "qwe";

        /*Map map = userManagerController.deleteRegiUser(userId, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void deleteRegiUser_Error(){
        String userId = "adesfaasdfa";

        /*Map map = userManagerController.deleteRegiUser(userId, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }


    @Test
    public void passRegisterUser(){
        String userId = "qwe";

        /*Map map = userManagerController.passRegisterUser(userId, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void passRegisterUser_Error(){
        /*String userId = "ankokshdgp0";

        Map map = userManagerController.passRegisterUser(userId, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void passBatchRegisterUsers(){
        /*String[] userIds = {"qwe", "ajax"};

        Map map = userManagerController.passBatchRegisterUsers(userIds, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
}