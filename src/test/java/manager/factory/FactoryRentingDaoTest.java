package manager.factory;

import com.cqupt.mis.erp.manager.factory.FactoryRentingDao;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
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
 * Created by 杨青 on 2016/8/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager =
        "transactionManager", defaultRollback = true)
@Transactional
public class FactoryRentingDaoTest {
    @Resource
    private FactoryRentingDao factoryRentingDao;

    @Test
    public void testFindFactoryRentsByMarketName() throws Exception {
        String userUnique = "panjun20151009090254";
        String marketName = "区域市场";

        List<FactoryCommonInfo> factoryCommonInfos = factoryRentingDao.
                findFactoryRentsByMarketName(userUnique, marketName);

        Assert.assertNotNull(factoryCommonInfos);
    }

    @Test
    public void testAddRentFactory() throws Exception {
        String userUnique = "周子渝20151009083255";
        String place = "本地市场";
        String factoryType = "小厂房";
        int beginTime = 1;
        float rentCost = (float) 12.3;
        int needPeriod = 23;

        int actualResult = factoryRentingDao.addRentFactory
                (userUnique, place, factoryType, beginTime, rentCost, needPeriod);
        int expectResult = 1;

        Assert.assertSame(expectResult, actualResult);
    }

}