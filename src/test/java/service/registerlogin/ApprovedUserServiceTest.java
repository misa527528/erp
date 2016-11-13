package service.registerlogin;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.service.registerlogin.ApprovedUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ApprovedUserServiceTest {
    @Resource
    private ApprovedUserDao approvedUserDao;
    @Resource
    private ApprovedUserService approvedUserService;

    @Test
    public void findUsernameByUserUnique() throws Exception {
        String userUnique = "lixu20151009095411";

        String userName = approvedUserDao.findUsernameByUserUnique(userUnique);

        Assert.assertEquals("李煦", userName);
    }

    @Test
    public void deleteApprovedUserById(){
        String approvedUserId = "这个用户是测试删除的";

        boolean result = approvedUserService.deleteApprovedUserById(approvedUserId);
        Assert.assertTrue(result);
    }

}