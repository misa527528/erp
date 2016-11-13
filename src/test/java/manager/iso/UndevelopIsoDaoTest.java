package manager.iso;

import com.cqupt.mis.erp.manager.iso.UndevelopIsoDao;
import com.cqupt.mis.erp.model.iso.ISOUndevelop;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UndevelopIsoDaoTest {
    @Resource
    private UndevelopIsoDao undevelopIsoDao;

    @Test
    public void findOneUndevelop() throws Exception {
        String userUnique = "bb14630512995485";
        String isoName = "PRODUCT9000质量认证";

        ISOUndevelop isoUndevelop = undevelopIsoDao.findOneUndevelop(userUnique, isoName);

        Assert.assertNotNull(isoUndevelop);
    }

}