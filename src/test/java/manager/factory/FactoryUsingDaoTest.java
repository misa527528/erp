package manager.factory;

import com.cqupt.mis.erp.manager.factory.FactoryUsingDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class FactoryUsingDaoTest {
    @Resource
    private FactoryUsingDao factoryUsingDao;

    @Test
    public void findUsingDetail() throws Exception {
        String userUnique = "雷小东20151009083255";
        String factoryId = "351";

        /*FactoryCommonInfo factoryCommonInfo = factoryUsingDao.findUsingDetail(userUnique, factoryId);

        Assert.assertNotNull(factoryCommonInfo);*/
    }

}