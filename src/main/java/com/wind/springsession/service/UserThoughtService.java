package com.wind.springsession.service;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.pojo.Success;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserThoughtService {

    Success addUserThought(HttpServletRequest request, String thoughtContent) throws CustomException;
}
