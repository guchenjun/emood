package com.hznu.emood.mapper;

import com.hznu.emood.model.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    void insertOrder(@Param("order") Order order, @Param("version") Long version);

    void updateOrderStatus(@Param("uuid") String uuid);

    boolean getStatusByUUID(@Param("uuid") String uuid);
}
