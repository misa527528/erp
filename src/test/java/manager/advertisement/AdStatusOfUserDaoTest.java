package manager.advertisement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 杨青 on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AdStatusOfUserDaoTest {
    @Test
    public void findFinishAdFlag() throws Exception {

    }

    @Test
    public void updateFinishAdFlag() throws Exception {

    }

    @Test
    public void updateFinishOrderFlag() throws Exception {

    }

    @Test
    public void findAdvertisementStatusOfUserByUserUniques() throws Exception {

    }

    @Test
    public void findAdvertisementStatusOfUserByUserUnique() throws Exception {

    }

    @Test
    public void updateChooseOrderFlag() throws Exception {

    }

    @Test
    public void findFinishOrderFlag() throws Exception {

    }

    @Test
    public void addAdStatusOfUser() throws Exception {

    }

    @Test
    public void findAllStatusVOByUserUnique() throws Exception {

    }

}