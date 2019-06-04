package com.hznu.emood.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.hznu.emood.mapper.MessageMapper;
import com.hznu.emood.model.Message;
import com.hznu.emood.model.MessageVO;
import com.hznu.emood.service.MessageService;
import com.hznu.emood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Reference
    UserService userService;

    @Override
    public List<MessageVO> listMessage() {
        List<Message> messageList = messageMapper.listMessage();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            String author = userService.getUsernameById(message.getUserId());
            MessageVO messageVO = new MessageVO();
            messageVO.setId(message.getId());
            messageVO.setAuthor(author);
            messageVO.setContent(message.getContent());
            messageVO.setGmtCreate(message.getGmtCreate());
            messageVO.setThumbsCount(new Random().nextInt(20));
            Boolean isVip = userService.getVipByUserId(message.getUserId());
            if (isVip) {
                messageVO.setVIP(true);
            } else {
                messageVO.setVIP(false);
            }
            messageVOList.add(messageVO);
        }
        return messageVOList;
    }

    @Override
    public void saveMessage(Message message) {
        message.setGmtCreate(new Date());
        messageMapper.insertMessage(message);
    }
}
