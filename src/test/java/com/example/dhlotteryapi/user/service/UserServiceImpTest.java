package com.example.dhlotteryapi.user.service;

import com.example.dhlotteryapi.user.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {
    UserService userService;
    @Test
    void testIntegration_login() throws Exception {
        UserDto user = new UserDto();
        user.setAccount("woaud2sla");
        user.setAccount("395273rse!");
        userService.login(user);
    }

}