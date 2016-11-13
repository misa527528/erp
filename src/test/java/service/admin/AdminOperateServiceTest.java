package service.admin;

import com.cqupt.mis.erp.service.admin.AdminOperateService;
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
 * Created by 杨青 on 2016/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AdminOperateServiceTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Resource
    private AdminOperateService adminOperateService;

    @Test
    public void testUpdatePWD() throws Exception {
        String adminID = "admin";
        String oldPWD = "123456";
        String newPWD = "cqupt";

        boolean actualResult = adminOperateService.
                updatePWD(adminID, oldPWD, newPWD);

        Assert.assertTrue(actualResult);
    }

    @Test
    public void adminUserLogin() throws Exception {
        String adminID = "admin";
        String password = "123456";

        Map map = adminOperateService.adminUserLogin(adminID, password,request);

        int expectMapStutus = 1;// 登登录成功map里面的status=1

        Assert.assertSame(expectMapStutus, map.get("status"));
    }

}