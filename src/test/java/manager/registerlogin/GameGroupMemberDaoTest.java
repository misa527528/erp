package manager.registerlogin;

import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GameGroupMemberDaoTest {
    @Resource
    private GameGroupMemberDao gameGroupMemberDao;

    @Test
    public void getStatusByUserUnique() throws Exception {
        String userUnique = "周子渝20151009083255";

        int actualStatus = gameGroupMemberDao.getStatusByUserUnique(userUnique);

        Assert.assertSame(1, actualStatus);
    }

}