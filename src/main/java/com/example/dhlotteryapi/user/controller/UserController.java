package com.example.dhlotteryapi.user.controller;

import com.example.dhlotteryapi.user.dto.UserDto;
import com.example.dhlotteryapi.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @ResponseBody
    @PostMapping("/order")
    public void login(@RequestBody UserDto user) throws Exception {
        userService.login(user);
    }
}
