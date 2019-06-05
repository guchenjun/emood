package com.hznu.emood.service;

import com.hznu.emood.model.Message;
import com.hznu.emood.model.MessageVO;

import java.util.List;
import java.util.Map;

public interface MessageService {

    Map<String, Object> listMessage(Integer pageNum);

    void saveMessage(Message message);
}
