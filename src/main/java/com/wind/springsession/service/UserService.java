package com.wind.springsession.service;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.model.User;
import com.wind.springsession.pojo.Success;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface UserService {

    Success register(String username, String password) throws CustomException;

    Success login(HttpServletRequest request, HttpServletResponse response,
                  String username, String password, boolean autoLogin) throws CustomException;

    Success logout(HttpServletRequest request, HttpServletResponse response) throws CustomException;

    List<User> getUserList(String page, String pageSize) throws CustomException;

    Success importUserList(List<User> userList) throws CustomException;

    User selectByUsername(String username);

    void setUserCookie(HttpServletResponse response, User user);

    void setUserCookie(HttpServletRequest request, HttpServletResponse response, User user);

    boolean autoLogin(HttpServletRequest request, HttpServletResponse response);

    void setUserSession(HttpServletRequest request, User user);

    void authFail(HttpServletResponse response) throws IOException;

    boolean userSessionIsNull(HttpServletRequest request);

    Success uploadAvatar(HttpServletRequest request, String avatar) throws IOException, CustomException;

    User getUserInfo(HttpServletRequest request);

    List<User> getAllUsers();

    Success deleteUser(int userId) throws CustomException;
}
