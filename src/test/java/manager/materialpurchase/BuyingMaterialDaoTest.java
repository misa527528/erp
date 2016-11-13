package manager.materialpurchase;

import com.cqupt.mis.erp.manager.materialpurchase.BuyingMaterialDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BuyingMaterialDaoTest {
    @Resource
    private BuyingMaterialDao buyingMaterialDao;

    @Test
    public void addMaterialPurchaseRecord() throws Exception {
        String userUnique = "bb14630512995485";
        String materialName = "R3";
        int materialNumber = 2;
        String wareHouseName = "1号仓库";
        int happendTime = 2;
        int endTime = 3;

        int result = buyingMaterialDao.addMaterialPurchaseRecord(userUnique, materialName, materialNumber,
                wareHouseName, happendTime, endTime);

        Assert.assertSame(1, result);
    }

}