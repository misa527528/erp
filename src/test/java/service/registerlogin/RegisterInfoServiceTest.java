package service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.registerlogin.RegisterInfoService;
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
 * Created by 杨青 on 2016/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class RegisterInfoServiceTest {
    @Resource
    private RegisterInfoService registerInfoService;

    @Test
    public void addRegister() throws Exception {
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setUserID("manm");
        registerInfo.setName("manutd");
        registerInfo.setPassword("manutd");
        registerInfo.setMajor("IMIS");
        registerInfo.setClassName("0311402");
        registerInfo.setStudentID("2014210862");
        registerInfo.setEmail("124578963@163.com");
        registerInfo.setTel("18883997845");
        registerInfo.setStatus("等待审批");

        boolean result = registerInfoService.addRegister(registerInfo);

        Assert.assertTrue(result);
    }

    @Test
    public void updatemodifyRegister() throws Exception {
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setUserID("2012211146");
        registerInfo.setName("manutd");
        registerInfo.setPassword("manutd");
        registerInfo.setMajor("IMIS");
        registerInfo.setClassName("0311402");
        registerInfo.setEmail("124578963@163.com");
        registerInfo.setTel("18883997845");

        boolean result = registerInfoService.updatemodifyRegister(registerInfo);

        Assert.assertTrue(result);
    }

    @Test
    public void updateModifyApprovedUser() throws Exception {
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setUserID("manman");
        registerInfo.setName("manutd");
        registerInfo.setPassword("manutd");
        registerInfo.setMajor("IMIS");
        registerInfo.setClassName("0311402");
        registerInfo.setStudentID("2014210862");
        registerInfo.setEmail("124578963@163.com");
        registerInfo.setTel("18883997845");

        boolean result = registerInfoService.updatemodifyRegister(registerInfo);

        Assert.assertTrue(result);
    }

    @Test
    public void findByUserId() throws Exception {
        String registerId = "manman";

        RegisterInfo registerInfo = registerInfoService.findByUserId(registerId);

        String expectedName = "yangqing";

        Assert.assertEquals(expectedName, registerInfo.getName());
    }

    @Test
    public void findAllRegister() throws Exception {
        List<RegisterInfo> registerInfos = registerInfoService.findAllRegister();
        int expectedListSize = 1;

        Assert.assertSame(expectedListSize, registerInfos.size());
    }

    @Test
    public void deleteRegisterById() throws Exception {
        String registerId = "manman";
        boolean expectedResult = true;

        boolean actualResult =  registerInfoService.deleteRegisterById(registerId);

        Assert.assertSame(expectedResult, actualResult);
    }

    @Test
    public void testDeleteRegisterByIdError(){
        String registerId = "testError";
        boolean expectedResult = false;

        boolean actualResult =  registerInfoService.deleteRegisterById(registerId);

        Assert.assertSame(expectedResult, actualResult);
    }

    @Test
    public void isUserExist(){
        String userId = "2012211166";

        boolean actualResult = registerInfoService.isUserExist(userId);

        Assert.assertTrue(actualResult);
    }
    @Test
    public void isUserExist_Error(){
        String userId = "mammmmmmmmmmmmmmmmmmmmmmmm";

        boolean actualResult = registerInfoService.isUserExist(userId);

        Assert.assertTrue(!actualResult);
    }

}