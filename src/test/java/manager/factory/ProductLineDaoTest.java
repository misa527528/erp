package manager.factory;

import com.cqupt.mis.erp.manager.factory.ProductLineDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager =
        "transactionManager", defaultRollback = true)
@Transactional
public class ProductLineDaoTest {
    @Resource
    private ProductLineDao productLineDao;

    @Test
    public void addProductLine() throws Exception {
        String userUnique = "周子渝20151008100727";
        String factoryId = "U52";
        String productLineType = "半自动生产线";
        String productName = "P2";

        int actualResult =  productLineDao.addProductLine
                (userUnique, factoryId, productLineType, productName);
        int expectedResult = 1;

        Assert.assertSame(expectedResult, actualResult);
    }

}