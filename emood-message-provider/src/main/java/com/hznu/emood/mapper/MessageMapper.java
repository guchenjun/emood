package com.hznu.emood.mapper;

import com.hznu.emood.model.Message;

import java.util.List;

public interface MessageMapper {

    List<Message> listMessage();

    void insertMessage(Message message);
}
