package manager.registerlogin;

import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
// TODO: 2016/8/23 该类的方法都还没有跑过测试
public class GameGroupDaoTest {
    @Resource
    private GameGroupDao gameGroupDao;

    @Test
    public void testFindGameCreatorByUserId() throws Exception {
        String userId = "lixu";

        GameGroupMemberInfo gameGroupCreator = gameGroupDao.findGameCreatorByUserId(userId);

        Assert.assertNotNull(gameGroupCreator);
    }

    @Test
    public void testFindGroupMemberSize(){
        String userUnique = "lixu20151009095411";

        int actualResult = gameGroupDao.findGroupMemberSize(userUnique);

        Assert.assertSame(1, actualResult);
    }

    // FIXME 空指针异常还没解决
    @Test
    public void testFindGroupMemberSizeNull(){
        String userUnique = "haimeiyouguozheligeba";
        System.out.print("打印gamegroupdao：" + gameGroupDao);
        int actualResult = gameGroupDao.findGroupMemberSize(userUnique);

        Assert.assertSame(-1000, actualResult);
    }

    @Test
    public void testAddGameGroup(){
        String groupName = "huluwa";
        String groupCreaterId = "Leipeng";
        int userNumbers = 3;
        int years = 3;
        int periodsOfOneYear = 3;
        int currentPeriod = 0;
        int autoPeriodFresh = 0;
        int atuoYearFresh = 0;
        String enableAutoPeriodFresh = "";
        String enableAutoYearFresh = "";

        int result = gameGroupDao.addGameGroup(groupName, groupCreaterId, userNumbers, years, periodsOfOneYear,
                currentPeriod, autoPeriodFresh, atuoYearFresh, enableAutoPeriodFresh, enableAutoYearFresh);

        Assert.assertSame(1, result);
    }

    @Test
    public void findPeriodOfYearByGroupName(){
        String groupName = "4test";

        int periodsOfOneYear = gameGroupDao.findPeriodOfYearByGroupName(groupName);

        Assert.assertSame(4, periodsOfOneYear);
    }

}