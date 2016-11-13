package manager.materialpurchase;

import com.cqupt.mis.erp.manager.materialpurchase.MaterialBasicDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialBasic;
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
 * Created by 杨青 on 2016/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MaterialBasicDaoTest {
    @Resource
    private MaterialBasicDao materialBasicDao;

    @Test
    public void findAllMaterialBasic() throws Exception {
        List<MaterialBasic> materialBasics = materialBasicDao.findAllMaterialBasic();
        int materialBasicsSize = materialBasics.size();

        Assert.assertSame(4, materialBasicsSize);
    }

}