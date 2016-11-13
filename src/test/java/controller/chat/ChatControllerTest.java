package controller.chat;

import com.cqupt.mis.erp.controller.chat.ChatController;
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
 * Created by 杨青 on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ChatControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private ChatController chatController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void addChat() throws Exception {
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String records = "添加一些聊天记录";

        /*Map map = chatController.addChat(records, request,  response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addChat_userId() throws Exception {
        request.getSession().setAttribute("userId", "qing");
        request.getSession().setAttribute("groupName", "ee");
        String records = "添加一些聊天记录";

      /*  Map map = chatController.addChat(records, request,  response);

        Assert.assertSame((String) map.get("message"), ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addChat_Error() throws Exception {
        String records = "添加一些聊天记录";

        /*Map map = chatController.addChat(records, request,  response);

        Assert.assertSame((String) map.get("message"), ReturnStatus.ERROR, map.get("status"));*/
    }


    @Test
    public void findAll() throws Exception {
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        request.getSession().setAttribute("groupName", "ee");

        /*Map map = chatController.findAll(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void findAll_userId() throws Exception {
        request.getSession().setAttribute("userId", "qing");
        request.getSession().setAttribute("groupName", "qing");

        /*Map map = chatController.findAll(request, response);

        Assert.assertSame((String) map.get("message"), ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void findAll_Error() throws Exception {
        request.getSession().setAttribute("groupName", "ee");

        /*Map map = chatController.findAll(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }


}