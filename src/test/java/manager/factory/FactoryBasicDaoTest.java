package manager.factory;

import com.cqupt.mis.erp.manager.factory.FactoryBasicDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class FactoryBasicDaoTest {
    @Resource
    private FactoryBasicDao factoryBasicDao;

    @Test
    public void findFactoryCapacity() throws Exception {
        String userUnique = "11120160426134707";
        String factoryId = "U73";

        int capacity = factoryBasicDao.findFactoryCapacity(userUnique, factoryId);

        Assert.assertTrue(capacity > 0);
    }

}