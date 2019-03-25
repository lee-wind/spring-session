package com.wind.springsession.service.impl;

import com.wind.springsession.dao.UserThoughtMapper;
import com.wind.springsession.exception.CustomException;
import com.wind.springsession.model.UserThought;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.UserThoughtService;
import com.wind.springsession.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserThoughtServiceImpl implements UserThoughtService {

    @Resource
    private UserThoughtMapper userThoughtMapper;

    @Override
    public Success addUserThought(HttpServletRequest request, String thoughtContent) throws CustomException {

        UserThought userThought = new UserThought();
        userThought.setUserId(Integer.parseInt(
                SessionUtil.getSessionString(request, SessionUtil.USER_SESSION_NAME)));
        userThought.setThoughtContent(thoughtContent);

        int row = userThoughtMapper.insertSelective(userThought);

        if(row == 1){
            return new Success("success", "add user thought success");
        }

        throw new CustomException("fail", "add thought fail");
    }
}
