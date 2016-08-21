package manager.registerlogin;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ApprovedUserDaoTest {
    @Resource
    private ApprovedUserDao approvedUserDao;

    @Test
    public void addRegister() throws Exception {
        //初始化一个Register对象所有的属性
        String userID = "你肯定没有见过这个ID";
        String name = "就叫这个吧";
        String password = "12345";
        String major = "IMIS";
        String className = "0311402";
        String studentID = "2014210587";
        String email = "";
        String tel = "";
        String typeId = "";

        int result = approvedUserDao.addRegister(userID, name, password,
                            major, className, studentID, email, tel, typeId);

        Assert.assertSame(result, 1);
    }

}