package controller.iso;

import com.cqupt.mis.erp.controller.iso.IsoController;
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
 * Created by 杨青 on 2016/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class IsoControllerTest {
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
    private IsoController isoController;

    @Test
    public void showISO() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");

       /* Map<String, Object> map = isoController.showISO(request, response);
        List<ISOUndevelop> isoUndevelopList = (List<ISOUndevelop>) map.get("isoUndevelopList");

        Assert.assertNotNull(isoUndevelopList.get(1));*/
    }
    @Test
    public void showISO_SessionNoUserUnique_Error() throws Exception {
        /*Map<String, Object> map = isoController.showISO(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void addISOToISODeveloping(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String isoName = "PRODUCT9000质量认证";

        /*Map map = isoController.addISOToISODeveloping(isoName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addISOToISODeveloping_SessionNoUserUnique_Error(){
        String isoName = "PRODUCT9000质量认证";

       /* Map map = isoController.addISOToISODeveloping(isoName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void updateISODevelopedStatusToOne(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

    }
}