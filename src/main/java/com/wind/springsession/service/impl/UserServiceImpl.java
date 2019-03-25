package com.wind.springsession.service.impl;

import com.wind.springsession.annotation.PrintSqlTime;
import com.wind.springsession.dao.UserMapper;
import com.wind.springsession.exception.CustomException;
import com.wind.springsession.model.User;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.UserService;
import com.wind.springsession.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

//    private static final log log = logFactory.getlog(UserServiceImpl.class);

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    //private static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "http://sun.wind.com:8080";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST";
    private static final String CONTENT_TYPE = "Content_Type";
    private static final String RESPONSE_JSON_TYPE = "application/json;charset=UTF-8";
    private static final String CHARSET = "UTF-8";
    private static byte[] AUTH_FAIL_RESPONSE = null;

    static {
        Map<String, String> result = new HashMap<>();
        result.put("code", "notLogin");
        result.put("message", "please login");

        try {
            AUTH_FAIL_RESPONSE = JSONUtil.toJSONString(result).getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Resource
    UserMapper userMapper;

    @Override
    public Success register(String username, String password) throws CustomException {

        ObjectUtil.isNotNull(username, "fail", "username cant not be null or empty");

        ObjectUtil.isNotNull(password, "fail", "password cant not be null or empty");

        User user = new User();
        user.setUsername(username);
        user.setPassword(EncryptionUtil.sha256HexEncrypt(password));

        try{
            int row = userMapper.insertUniqueUser(user);
            if(row == 0){
                throw new CustomException("fail", "user already existed!");
            }
            if(row == 1){
                return new Success("success", "register success");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
        throw new CustomException("fail", "register fail");
    }

    @Override
    public Success login(HttpServletRequest request, HttpServletResponse response,
                         String username, String password, boolean autoLogin)throws CustomException {


        ObjectUtil.isNotNull(username, "fail", "username cant not be null or empty");

        ObjectUtil.isNotNull(password, "fail", "password cant not be null or empty");

        User user = userMapper.selectByUsernameAndPassword(username,
                EncryptionUtil.sha256HexEncrypt(password));


        log.info("user: {}", JSONUtil.toJSONString(user));

        ObjectUtil.isNotNull(user, "emptyUser", "user is not exist");

        setUserSession(request, user);

        if(autoLogin){
            setUserCookie(response, user);
//            setUserCookie(request, response, user);
        }else{
            removeUserCookie(request, response);
        }

        return new Success("success", "login success");
    }

    @Override
    public Success logout(HttpServletRequest request, HttpServletResponse response) throws CustomException {

        if(userSessionIsNull(request)){
            throw new CustomException("notLogin", "please login!");
        }

        SessionUtil.removeSession(request, SessionUtil.USER_SESSION_NAME);

        removeUserCookie(request, response);

        return new Success("success", "logout success");

    }


    @Override
    public void setUserSession(HttpServletRequest request, User user){

        log.info("setSession: {}", user.getUserId());
        SessionUtil.setSession(request, SessionUtil.USER_SESSION_NAME, user.getUserId());
    }

    @Override
    public void setUserCookie(HttpServletResponse response, User user) {

        String autoLoginKey = EncryptionUtil.sha256HexEncrypt(user.getPassword());

        CookieUtil.addCookie(response, CookieUtil.USER_COOKIE_NAME, user.getUsername(),
                CookieUtil.USER_COOKIE_MAX_AGE, CookieUtil.USER_COOKIE_DOMAIN,
                CookieUtil.USER_COOKIE_PATH);
        CookieUtil.addCookie(response, CookieUtil.USER_COOKIE_KEY, autoLoginKey,
                CookieUtil.USER_COOKIE_MAX_AGE, CookieUtil.USER_COOKIE_DOMAIN,
                CookieUtil.USER_COOKIE_PATH);
    }

    @Override
    public void setUserCookie(HttpServletRequest request, HttpServletResponse response, User user) {

        log.info("{}", request.getServerPort());

        String origin  = request.getHeader("Origin");

        log.info("origin: {}", origin);

        String domain = origin.substring(origin.indexOf("/") + 2, origin.lastIndexOf(":"));

        log.info("domain: {}", domain);

        String autoLoginKey = EncryptionUtil.sha256HexEncrypt(user.getPassword());

        CookieUtil.addCookie(response, CookieUtil.USER_COOKIE_NAME, user.getUsername(),
                CookieUtil.USER_COOKIE_MAX_AGE, domain,
                CookieUtil.USER_COOKIE_PATH);
        CookieUtil.addCookie(response, CookieUtil.USER_COOKIE_KEY, autoLoginKey,
                CookieUtil.USER_COOKIE_MAX_AGE, domain,
                CookieUtil.USER_COOKIE_PATH);
    }

    private void removeUserCookie(HttpServletRequest request, HttpServletResponse response) {

        CookieUtil.removeCookie(request, response, CookieUtil.USER_COOKIE_NAME,
                CookieUtil.USER_COOKIE_DOMAIN, CookieUtil.USER_COOKIE_PATH);
        CookieUtil.removeCookie(request, response, CookieUtil.USER_COOKIE_KEY,
                CookieUtil.USER_COOKIE_DOMAIN, CookieUtil.USER_COOKIE_PATH);
    }

    @Override
    public boolean userSessionIsNull(HttpServletRequest request) {

        String userId = SessionUtil.getSessionString(request, SessionUtil.USER_SESSION_NAME);

        log.info("userId in session: {}", userId);

        return ObjectUtil.isNull(userId);
    }


    @Override
    public boolean autoLogin(HttpServletRequest request, HttpServletResponse response) {

        String username = CookieUtil.getCookieValue(request,
                CookieUtil.USER_COOKIE_NAME);
        String key = CookieUtil.getCookieValue(request,
                CookieUtil.USER_COOKIE_KEY);

        log.info("自动登录, cookie: username: {}, key: {}", username, key);

        if(!ObjectUtil.isNull(username) && !ObjectUtil.isNull(key)){
            User user = selectByUsername(username);

            log.info("自动登录, user: {}", JSONUtil.toJSONString(user));

            if (!ObjectUtil.isNull(user) &&
                    EncryptionUtil.sha256HexEncrypt(user.getPassword()).equals(key)){

//                setUserCookie(response, user);
//
                setUserSession(request, user);

                return true;
            }
        }
        return false;
    }

    @Override
    public void authFail(HttpServletResponse response) throws IOException {

//        response.setHeader(CONTENT_TYPE, RESPONSE_JSON_TYPE);
//        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
//        response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE);
//        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_VALUE);
        OutputStream outputStream = response.getOutputStream();

        outputStream.write(AUTH_FAIL_RESPONSE);

        outputStream.close();
    }

    @Override
    public User getUserInfo(HttpServletRequest request) {

        int userId = Integer.parseInt(SessionUtil.getSessionString(
                request, SessionUtil.USER_SESSION_NAME));

        User user = userMapper.getUserInfoByUserId(userId);

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return userMapper.getAllUsers();
    }

    @Override
    public Success deleteUser(int userId) throws CustomException {

        int row = userMapper.deleteByPrimaryKey(userId);
        if(row == 0){
            return new Success("success", "delete success");
        }
        throw new CustomException("fail", "delete fail");
    }


    @Override
    public Success uploadAvatar(HttpServletRequest request, String avatar) throws IOException, CustomException {

        log.info("avatar: {}", avatar);

        int userId = Integer.parseInt(SessionUtil.getSessionString(request, SessionUtil.USER_SESSION_NAME));

        String  avatarSrc = FileUploadUtil.multipartFileUpload(
                BASE64DecodedMultipartFileUtil.base64ToMultipart(avatar),
                ServerConfigUtil.fileServerDir,
                ServerConfigUtil.userAvatarDir,
                ServerConfigUtil.userAvatarMaxSize);

        log.info("avatarSrc: {}", avatarSrc);

        User user = new User();
        user.setUserId(userId);
        user.setAvatar(avatarSrc);
        user.setUpdateTime(new Date());

        int row = userMapper.updateByPrimaryKeySelective(user);

        if(row != 1){
            throw new CustomException("fail", "update avatar failed");
        }

        return new Success("success", avatarSrc);
    }


    @Override
    @PrintSqlTime
    public List<User> getUserList(String page, String pageSize) throws CustomException {

        int start = ObjectUtil.convertToInt(page, "fail", "page's format is wrong");

        int limit = ObjectUtil.convertToInt(pageSize, "fail", "pageSize's format is wrong");
        int offset = (start - 1) * limit;

        log.info("limit: {}, offset: {}", limit, offset);

        return userMapper.getUserList(limit, offset);
    }

    @Override
    public Success importUserList(List<User> userList) throws CustomException {

        for(User user: userList){

            ObjectUtil.isNotNull(user.getUsername(), "fail", "username cant not be null or empty");

            ObjectUtil.isNotNull(user.getPassword(), "fail", "password cant not be null or empty");

            user.setPassword(EncryptionUtil.sha256HexEncrypt(user.getPassword()));
        }

        int row =  userMapper.importUserList(userList);

        int ignoreRow = userList.size() - row;

        return new Success("success", "insert row: " + row
            + ", ignore row: " + ignoreRow);
    }

    @Override
    public User selectByUsername(String username){

        return userMapper.selectByUsername(username);
    }

}
