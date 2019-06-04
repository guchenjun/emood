package com.hznu.emood.service;

import com.hznu.emood.model.User;

public interface UserService {

    User getUserByUsername(String username);

    void saveUser(User user);

    User getUserById(String userId);

    String getUsernameById(Long userId);

    Boolean getVipByUserId(Long userId);

    void updateUserVip(String userId);
}
