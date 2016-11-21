package com.cqupt.mis.erp.controller.registerlogin;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import com.cqupt.mis.erp.utils.validator.SecurityCode;
import com.cqupt.mis.erp.utils.validator.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/30.
 */
@Controller("securityCodeImageController")
@RequestMapping("/securityCodeImage")
public class SecurityCodeImageController {
    // 图片流
    private ByteArrayInputStream imageStream;

    @RequestMapping("/getSecurityCode.do")
    public void getSecurityCode(HttpServletRequest request, HttpServletResponse response){
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);

        request.getSession().setAttribute("SESSION_SECURITY_CODE", securityCode);
        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "获取验证码", imageStream);
        JSONUtils.toJSON(map, response);
    }

}
