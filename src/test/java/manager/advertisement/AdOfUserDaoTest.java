package manager.advertisement;

import com.cqupt.mis.erp.manager.advertisement.AdOfUserDao;
import com.cqupt.mis.erp.model.advertisement.Advertisement;
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
 * Created by 杨青 on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AdOfUserDaoTest {
    @Resource
    private AdOfUserDao adOfUserDao;

    @Test
    public void testFindAdvertisementByMarketName() throws Exception {
        List<Advertisement> ads =  adOfUserDao.findAdvertisementByMarketName("亚洲市场", "吴艳琼20151008100727");
        int result = ads.size(); //list内容太多懒得判断，只简单判断有几个对象
        Assert.assertSame(4, result);
    }

    @Test
    public void updateAdvertisementForProduct() throws Exception {

    }

    @Test
    public void findSummaryMoney() throws Exception {

    }

    @Test
    public void findAdvertisementMoneyOfUsers() throws Exception {

    }

    @Test
    public void findUserAdvertisementMoney() throws Exception {

    }

    @Test
    public void findMarketnameAndProductname() throws Exception {

    }

    @Test
    public void addAdvertisementList() throws Exception {

    }

    @Test
    public void findAlreadAdvertisement() throws Exception {

    }

    @Test
    public void findMoneyIntoMarket() throws Exception {

    }

    @Test
    public void findProductNameByMarket() throws Exception {

    }

}