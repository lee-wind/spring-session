package com.wind.springsession.controller;

import com.wind.springsession.annotation.AuthUser;
import com.wind.springsession.annotation.CSRFAnnotation;
import com.wind.springsession.exception.CustomException;
import com.wind.springsession.model.User;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public Success register(String username, String password) throws CustomException {


        return userService.register(username, password);
    }

    @PostMapping("/login")
    public Success login(HttpServletRequest request, HttpServletResponse response,
                      String username, String password, boolean autoLogin) throws CustomException {

        return userService.login(request, response, username, password, autoLogin);

    }

    @PostMapping("/logout")
    public Success logout(HttpServletRequest request, HttpServletResponse response) throws CustomException {

        return userService.logout(request, response);
    }

    @GetMapping("/info")
    public User getUserInfo(HttpServletRequest request){

        return userService.getUserInfo(request);
    }

    @PostMapping("/uploadAvatar")
    public Success uploadAvatar(HttpServletRequest request, String avatar) throws CustomException, IOException {

        return userService.uploadAvatar(request, avatar);
    }



    @GetMapping("/getUserList")
    public List<User> getUserList(String page, String pageSize) throws CustomException {

        return userService.getUserList(page, pageSize);
    }

    @PostMapping("/importUserList")
    public Success importUserList(@RequestBody List<User> userList) throws CustomException {

        return userService.importUserList(userList);
    }

    @AuthUser
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

    @CSRFAnnotation
    @PostMapping("/deleteUser")
    public Success deleteUser(Integer userId) throws CustomException {

        return userService.deleteUser(userId);
    }

}
