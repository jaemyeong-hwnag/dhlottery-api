package com.example.dhlotteryapi.user.service;

import com.example.dhlotteryapi.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void login(UserDto user) throws Exception;
    void logout();
    void checkDHServer() throws Exception;
}
