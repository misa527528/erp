package manager.bank;

import com.cqupt.mis.erp.manager.bank.LoanOfUserDao;
import com.cqupt.mis.erp.model.bank.LoanOfUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 杨青 on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LoanOfUserDaoTest {
    @Resource
    private LoanOfUserDao loanOfUserDao;

    @Test
    public void testfFindALoanOfUserByUserUniqueAndLoanID() throws Exception {
        String userUnique = "11120160425165520";
        int loanId = 949;
        LoanOfUser actualLoanOfUser =
                loanOfUserDao.findALoanOfUserByUserUniqueAndLoanID(userUnique, loanId);

        LoanOfUser expectedLoanOfUser = new LoanOfUser();
        expectedLoanOfUser.setLoanID(949);
        expectedLoanOfUser.setUserUnique("11120160425165520");
        expectedLoanOfUser.setLoanTypeName("长期贷款");
        expectedLoanOfUser.setBeginTime(1);
        expectedLoanOfUser.setEndTime(20);
        expectedLoanOfUser.setStatus(0);
        expectedLoanOfUser.setMoney(20.0);

        //没有重写对象的equals方法，简单地只取对象中的某些字段来判断
        Assert.assertEquals(expectedLoanOfUser.getBeginTime(), actualLoanOfUser.getBeginTime());
    }

    @Test
    public void testFindLoanOfUserByUserUnique() throws Exception {
        String userUnique = "11120160425165520";
        List<LoanOfUser> loanOfUsers = loanOfUserDao.findLoanOfUserByUserUnique(userUnique);

        //简单断言取得的数组中是不是有两个对象
        Assert.assertSame(2, loanOfUsers.size());
    }

    @Test
    public void testFindLoanOfUserByUserUniqueAndStatus() throws Exception {
        String userUnique = "11120160425165520";
        int status = 0;

        List<LoanOfUser> loanOfUsers =
                loanOfUserDao.findLoanOfUserByUserUniqueAndStatus(userUnique, status);

        int expectedNumOfLoanOfUser = 2;

        Assert.assertSame(expectedNumOfLoanOfUser, loanOfUsers.size());
    }

    @Test
    public void testFindLoanOfUserByUserUniqueAndStatusAndLoanTypeName() throws Exception {
        String userUnique = "11120160426134707";
        String loanTypeName = "长期贷款";
        int status = 0;

       List<LoanOfUser> loanOfUsers =
               loanOfUserDao.findLoanOfUserByUserUniqueAndStatusAndLoanTypeName
                       (userUnique, status, loanTypeName);

        int expectLoanOfUsersSize = 2;

        Assert.assertSame(expectLoanOfUsersSize, loanOfUsers.size());
    }

    @Test
    public void testFindLoanOfUserByUserUniqueAndLoanTypeName() throws Exception {
        String userUnique = "11120160426134707";
        String loanTypeName = "长期贷款";
        List<LoanOfUser> loanOfUsers =
                loanOfUserDao.findLoanOfUserByUserUniqueAndLoanTypeName(userUnique, loanTypeName);

        int expectLoanOfUsersSize = 2;

        Assert.assertSame(expectLoanOfUsersSize, loanOfUsers.size());
    }

    @Test
    public void testUpdateLoanOfUser() throws Exception {
        int loadId = 949;
        int status = 1;
        String userUnique = "11120160425165520";

        int actualResult = loanOfUserDao.updateLoanOfUser(userUnique, loadId, status);
        int expectedResult = 1;

        Assert.assertSame(expectedResult, actualResult);
    }

}