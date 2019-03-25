package com.wind.springsession.controller;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.UserThoughtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userThought")
public class UserThoughtController {

    @Resource
    private UserThoughtService userThoughtService;

    @PostMapping("/addThought")
    public Success addUserThought(HttpServletRequest request, String thoughtContent) throws CustomException {

        return userThoughtService.addUserThought(request, thoughtContent);
    }
}
