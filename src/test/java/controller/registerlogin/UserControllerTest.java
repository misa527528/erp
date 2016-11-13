package controller.registerlogin;

import com.cqupt.mis.erp.controller.registerlogin.UserController;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
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
 * Created by 杨青 on 2016/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private UserController userController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void addRegister() throws Exception {
        RegisterInfo registerInfo = new RegisterInfo();
        // 数据表中这四个四段设置为NOT NULL，所以必须赋值，其他字段可以不赋值
        registerInfo.setUserID("hhhjjj");
        registerInfo.setName("hhh");
        registerInfo.setPassword("hhhjjj");
        registerInfo.setStudentID("0311420456");

      /* Map map = userController.addRegister(registerInfo, request, response);

        Assert.assertSame(1, map.get("status"));*/
    }

    @Test
    public void modidyUser_RegisterSuccess(){
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setUserID("manman");
        registerInfo.setName("manman");
        registerInfo.setPassword("147852");
        registerInfo.setStudentID("0311420456");

     //   Map map = userController.modidyUser(registerInfo, response);

     //   Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));
    }
    @Test
    public void modidyUser_ApprovedUserSuccess(){
        RegisterInfo approvedUser = new RegisterInfo();
        approvedUser.setUserID("2012211130");
        approvedUser.setName("manman");
        approvedUser.setPassword("147852");
        approvedUser.setStudentID("0311420456");

    //    Map map = userController.modidyUser(approvedUser, response);

      //  Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));
    }
    @Test
    public void modidyUser_Error(){
        RegisterInfo approvedUser = new RegisterInfo();
        approvedUser.setUserID("没有这个ID");
        approvedUser.setName("manman");
        approvedUser.setPassword("147852");
        approvedUser.setStudentID("0311420456");

    //    Map map = userController.modidyUser(approvedUser, response);

      //  Assert.assertSame(ReturnStatus.ERROR, map.get("status"));
    }

    @Test
    public void isUserExist(){
        String userId = "alei_firefox";

//        Map map = userController.isUserExist(userId, response);
//
//        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));
    }
    @Test
    public void isUserExist_Not(){
        String userId = "";

      /*  Map map = userController.isUserExist(userId, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getAllRegisters(){
        Map map;

    //    map = userController.getAllRegisters(response);

    //    Assert.assertNotNull("该方法只有register表为空时才Assert报错", map.get("data"));
    }

}