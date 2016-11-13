package controller.bank;

import com.cqupt.mis.erp.controller.bank.LoanController;
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
 * Created by 杨青 on 2016/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LoanControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private LoanController loanController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void isAllowLoan_ShortLoan() throws Exception{
        request.getSession().setAttribute("userUnique", "321147071223586965");
        String loanType = "短期贷款";

/*        Map<String, Object> map = loanController.isAllowLoan(loanType, request, response);
        boolean isAllowed = (boolean) map.get("isAllowed");

        Assert.assertSame(true, isAllowed);*/
    }
    @Test
    public void isAllowLoan_LongLoan() throws Exception {
        request.getSession().setAttribute("userUnique", "321147071223586965");
        String loanType = "长期贷款";

       /* Map<String, Object> map = loanController.isAllowLoan(loanType, request, response);
        boolean isAllowed = (boolean) map.get("isAllowed");

        Assert.assertSame(false, isAllowed);*/
    }
    @Test
    public void isAllowLoan_Usury() throws Exception{
        request.getSession().setAttribute("userUnique", "321147071223586965");
        String loanType = "高利贷";

        /*Map<String, Object> map = loanController.isAllowLoan(loanType, request, response);
        boolean isAllowed = (boolean) map.get("isAllowed");

        Assert.assertTrue(isAllowed);*/
    }


    @Test
    public void applyLoan_ShortLoan() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String loanType = "短期贷款";
        Integer loanTime = 2;
        Double loanMoney = 1.0;

        /*Map map = loanController.applyLoan(loanType, loanTime, loanMoney, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void applyLoan_LongLoan() throws Exception {
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String loanType = "长期贷款";
        Integer loanTime = 2;
        Double loanMoney = 1.0;

        /*Map map = loanController.applyLoan(loanType, loanTime, loanMoney, request, response);

        Assert.assertSame((String) map.get("message"), ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void applyLoan_Usury() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String loanType = "高利贷";
        Integer loanTime = 2;
        Double loanMoney = 1.0;

       /* Map map = loanController.applyLoan(loanType, loanTime, loanMoney, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void applyLoan_Error() throws Exception {
        request.getSession().setAttribute("userUnique", "321147071223586965");
        Integer loanTime = 2;
        Double loanMoney = 1.0;
        String loanType = "长期贷款";

        /*Map map = loanController.applyLoan(loanType, loanTime, loanMoney, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void findLoan(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String loanStatus = "未还贷款";
        String loanType = "长期贷款";

        /*Map map = loanController.findLoan(loanStatus, loanType, request, response);
        List<LoanOfUser> loanList = (List<LoanOfUser>) map.get("loanList");

        Assert.assertSame(2, loanList.size());*/
    }

    @Test
    public void returnLoan(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        int loanId = 1117;

        /*Map map = loanController.returnLoan(loanId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
}