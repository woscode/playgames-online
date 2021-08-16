package com.wos.playgamesonline.service;

import com.wos.playgamesonline.model.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);
    User findByNickname(String nickname);
}
