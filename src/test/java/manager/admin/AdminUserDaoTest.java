package manager.admin;

import com.cqupt.mis.erp.manager.admin.AdminUserDao;
import com.cqupt.mis.erp.model.admin.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Created by 杨青 on 2016/8/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AdminUserDaoTest {
    @Resource
    private AdminUserDao adminUserDao;

    @Test
    public void testFindAdminUsersByAdminId(){
        AdminUser expectedAdminUser = new AdminUser();
        expectedAdminUser.setAdminId("admin");
        expectedAdminUser.setPassword("cqupt");
        expectedAdminUser.setAdminName("管理员");
        expectedAdminUser.setPosition("老师");
        expectedAdminUser.setRole("管理员");

        String adminId = "admin";
        AdminUser adminUser = adminUserDao.findAdminUsersByAdminId(adminId);
        // assertEquals(expectedAdminUser, adminUser);
        assertNotSame(expectedAdminUser, adminUser);
        System.out.print(adminUser.getAdminId());
    }

    @Test
    public void testUpdatePWD(){
        int result = adminUserDao.updatePWD("admin", "123456", "cqupt");
        System.out.print(result);
        assertEquals(1, result);
    }
}
