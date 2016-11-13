package service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class GameGroupServiceImplTest {
    @Resource
    private GameGroupService gameGroupService;

    @Test
    public void showGameGroupList() throws Exception {

    }

    @Test
    public void showGameGroupList1() throws Exception {

    }

    @Test
    public void showOthersPage() throws Exception {

    }

    @Test
    public void addGameGroup() throws Exception {
        GameGroupInfo gameGroupInfo = new GameGroupInfo();
        gameGroupInfo.setGroupName("huluwa");
        gameGroupInfo.setGroupCreaterId("Leipeng");
        gameGroupInfo.setUserNumbers(4);
        gameGroupInfo.setYears(3);
        gameGroupInfo.setPeriodsOfOneYear(3);
        gameGroupInfo.setCurrentPeriod(0);
        gameGroupInfo.setAutoPeriodFresh(0);
        gameGroupInfo.setAtuoYearFresh(0);
        gameGroupInfo.setEnableAutoPeriodFresh("");
        gameGroupInfo.setEnableAutoYearFresh("");

        boolean result = gameGroupService.addGameGroup(gameGroupInfo);

        Assert.assertTrue(result);
    }

    @Test
    public void findGameGroupInfoByGroupName() throws Exception {

    }

    @Test
    public void deleteGameGroupByGroupName() throws Exception {

    }

    @Test
    public void findYearsByGroupName() throws Exception {

    }

    @Test
    public void isYearBegin() throws Exception {

    }

    @Test
    public void showGameGroups() throws Exception {

    }

    @Test
    public void findGameGroupMemberStatusByGroupName() throws Exception {

    }

    @Test
    public void endPlayGame() throws Exception {

    }

    @Test
    public void delete_GameGroupByGroupName() throws Exception {

    }

    @Test
    public void addChangeToHistory() throws Exception {

    }

    @Test
    public void isGroupCreator() throws Exception {
        String userId = "lixu";

        boolean actualResult = gameGroupService.isGroupCreator(userId);
        Assert.assertTrue(actualResult);
    }
    @Test
    public void isGroupCreator_Error() throws Exception {
        String userId = "没有这个userid的吧";

        boolean actualResult = gameGroupService.isGroupCreator(userId);
        Assert.assertTrue(!actualResult);
    }

    @Test
    public void isGameRunning() throws Exception {

    }

    @Test
    public void findPeriodOfYear() throws Exception{
        String userUnique = "lixu20151009095411";

        int periodOfYear = gameGroupService.findPeriodOfYear(userUnique);

        Assert.assertSame(8, periodOfYear);
    }
}