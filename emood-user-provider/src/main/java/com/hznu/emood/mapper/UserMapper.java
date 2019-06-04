package com.hznu.emood.mapper;

import com.hznu.emood.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User getUserByUsername(@Param("username") String username);

    void insertUser(User user);

    User getUserById(@Param("userId") String userId);

    String getUsernameById(@Param("userId") Long userId);

    Boolean getVipByUserId(@Param("userId") Long userId);

    void updateUserVip(@Param("userId") String userId);
}
