package manager.factory;

import com.cqupt.mis.erp.manager.factory.FactoryMakingDao;
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
public class FactoryMakingDaoTest {
    @Resource
    private FactoryMakingDao factoryMakingDao;

    @Test
    public void testFindFactoryMakings() throws Exception {
        String userUnique = "321147071223586965";
        List<FactoryCommonInfo> actualFactoryCommonInfoList =
                factoryMakingDao.findFactoryMakings(userUnique);

        int expectedFFactoryCommonInfoListSize = 1;

        Assert.assertSame(expectedFFactoryCommonInfoListSize,
                actualFactoryCommonInfoList.size());
    }

    @Test
    public void updateFactoryStatus() throws Exception {
        String userUnique = "周子渝20151008100727";
        int factoryId = 7;
        int status = 1;

        int actualResult = factoryMakingDao.
                updateFactoryStatus(userUnique, factoryId, status);
        int expectedResult = 1;

        Assert.assertSame(expectedResult, actualResult);
    }

}