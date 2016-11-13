package service.market;

import com.cqupt.mis.erp.service.market.MarketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MarketServiceImplTest {
    @Resource
    private MarketService marketService;

    @Test
    public void updateStopDevelopingMarket() throws Exception {
        String userUnique = "qing147254658863377";
        String marketName = "亚洲市场";

        boolean result = marketService.updateStopDevelopingMarket(userUnique, marketName);

        Assert.assertTrue(result);
    }

    @Test
    public void updateStartDevelopingMarket() throws Exception {
        String userUnique = "qing147254658863377";
        String marketName = "亚洲市场";

        boolean result = marketService.updateStartDevelopingMarket(userUnique, marketName);

        Assert.assertTrue(result);
    }

}