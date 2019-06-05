package com.hznu.emood.mapper;

import com.hznu.emood.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    List<Message> listMessage(@Param("offset") Integer offset);

    void insertMessage(Message message);

    Integer getCount();
}
