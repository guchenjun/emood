package com.hznu.emood.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hznu.emood.annotation.UserLoginToken;
import com.hznu.emood.model.Message;
import com.hznu.emood.model.MessageVO;
import com.hznu.emood.model.R;
import com.hznu.emood.service.MessageService;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "留言板模块")
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Reference
    private MessageService messageService;

    @Reference
    private UserService userService;

    @UserLoginToken
    @ApiOperation(value = "留言板列表请求", notes = "返回留言板所有列表")
    @GetMapping("/list")
    public R messageList() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<MessageVO> messageVOList = messageService.listMessage();
            map.put("msgList", messageVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(400, "err");
        }
        return R.ok(200, "ok", map);
    }


    @UserLoginToken
    @ApiOperation(value = "新增留言板请求", notes = "返回增加结果")
    @PostMapping("/add")
    public R addMessage(HttpServletRequest request, @RequestParam("content") String content) {
        Message message = new Message();
        try {
            String token = request.getHeader("Authorization");
            String userId = JwtUtil.getId(token);
            message.setContent(content);
            message.setUserId(Long.parseLong(userId));
            message.setGmtCreate(new Date());
            message.setAuthor(userService.getUsernameById(Long.parseLong(userId)));
            messageService.saveMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(400, "添加留言板失败!" ,message);
        }
        return R.ok(200, "添加留言板成功!", message);
    }
}
