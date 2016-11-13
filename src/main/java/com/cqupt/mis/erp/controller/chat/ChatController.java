package com.cqupt.mis.erp.controller.chat;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.chatroom.ChatRoom;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.service.chatroom.ChatRoomService;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/15.
 */
@Controller("chatController")
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatRoomService chatRoomService;
    @Resource
    private LoginService loginService;

    @RequestMapping("/addChat.do")
    public void addChat(String record, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        String userId = (String) request.getSession().getAttribute("userId");
        String groupName = (String) request.getSession().getAttribute("groupName");

        if (userUnique != null && !"".equals(userUnique)){
            chatRoomService.addChatRecordByUserUnique(userUnique, groupName, record);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "添加成功", null);
            JSONUtils.toJSON(map, response);
            return;
        } else {
            if (userId != null && !"".equals(userId)){
                ApprovedUserInfo user = loginService.findApprovedUserByUserId(userId);
                chatRoomService.addChatRecordByUserId(groupName, user.getName(), record);
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "添加成功", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "失败", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/findAll.do")
    public Map findAll(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        String userName = (String) request.getSession().getAttribute("userName");

        if (userUnique != null && !"".equals(userUnique)){
            List<ChatRoom> records = chatRoomService.findChatRecords(userUnique);

            map.put("status", ReturnStatus.SUCCESS);
            map.put("message", null);
            map.put("userName", userName);
            map.put("records", records);
            JSONUtils.toJSON(map, response);
            return map;
        }

        String userId = (String) request.getSession().getAttribute("userId");
        if (userId != null && !"".equals(userId)){
            ApprovedUserInfo approvedUserInfo = loginService.findApprovedUserByUserId(userId);
            List<ChatRoom> records = chatRoomService.findChatRecords(userId);

            map.put("status", ReturnStatus.SUCCESS);
            map.put("message", null);
            map.put("userName", approvedUserInfo.getName());
            map.put("records", records);
            JSONUtils.toJSON(map, response);
            return map;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "查找失败", null);
        JSONUtils.toJSON(map, response);
        return map;
    }
}
