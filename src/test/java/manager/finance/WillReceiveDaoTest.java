package manager.finance;

import com.cqupt.mis.erp.manager.finance.WillReceiveDao;
import org.junit.Assert;
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
public class WillReceiveDaoTest {
    @Resource
    private WillReceiveDao willReceiveDao;

    @Test
    public void insert() throws Exception {
        String userUnique = "1120160425165520";
        Double money = 45.0;
        Integer beginTime = 2;
        Integer endTime = 4;
        String willReceiveDescription = "说明";
        String note = "备注";

        int result = willReceiveDao.insert(userUnique, money, beginTime, endTime, willReceiveDescription, note);

        Assert.assertSame(1, result);
    }

}