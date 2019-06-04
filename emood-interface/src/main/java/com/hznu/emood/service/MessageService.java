package com.hznu.emood.service;

import com.hznu.emood.model.Message;
import com.hznu.emood.model.MessageVO;

import java.util.List;

public interface MessageService {

    List<MessageVO> listMessage();

    void saveMessage(Message message);
}
