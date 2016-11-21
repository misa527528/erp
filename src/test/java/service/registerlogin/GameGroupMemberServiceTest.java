package service.registerlogin;

import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
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
public class GameGroupMemberServiceTest {
    @Resource
    private GameGroupMemberService gameGroupMemberService;

    @Test
    public void showGameGroupMemberList() throws Exception {

    }

    @Test
    public void pageTagForGamegroupMember() throws Exception {

    }

    @Test
    public void addJoinInGroup() throws Exception {

    }

    @Test
    public void deleteExitGroup() throws Exception {
        String groupName = "赵翼20151009083255";
        String userId = "赵翼";

        boolean result = gameGroupMemberService.deleteExitGroup(groupName, userId);

        Assert.assertTrue(result);
    }

    @Test
    public void updateUserUnqiqueAndCurrentPeriod() throws Exception {

    }

    @Test
    public void findGroupNameByUserId() throws Exception {

    }

    @Test
    public void findGameGroupUserMessageByGroupName() throws Exception {

    }

    @Test
    public void findUserUniqueByUserId() throws Exception {

    }

    @Test
    public void findCurrentPeriod() throws Exception {

    }

    @Test
    public void findStatusByUserUnique() throws Exception {

    }

    @Test
    public void startGameForward() throws Exception {

    }

    @Test
    public void startGameFilter() throws Exception {

    }

    @Test
    public void startGameForwardToMain() throws Exception {

    }

    @Test
    public void updateGameGroupMemberNumber() throws Exception {

    }

    @Test
    public void exitGroupReload() throws Exception {

    }

    @Test
    public void findUsersByGroupName() throws Exception {

    }

    @Test
    public void isGroupMember() throws Exception {

    }

}