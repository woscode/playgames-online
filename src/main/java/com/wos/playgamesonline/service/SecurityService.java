package com.wos.playgamesonline.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
