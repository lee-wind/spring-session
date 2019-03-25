package com.wind.springsession.controller;

import com.wind.springsession.annotation.AuthUser;
import com.wind.springsession.exception.CustomException;
import com.wind.springsession.util.FileUploadUtil;
import com.wind.springsession.util.ServerConfigUtil;
import com.wind.springsession.model.Skill;
import com.wind.springsession.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index() {

        int a[] = new int[2];

//        try{
//            logger.info(a[3]+"");
//        }catch (Exception e){
//           throw new CustomException("888", "array out of range");
//        }

        return "Hello, Spring-Session!";
        //return "//扩展 HttpServlet 类";
    }

    @GetMapping("/get")
    public Map<String, String> get(String name, String sex){

        Map<String, String> result = new HashMap<>();

        result.put("success", "getData成功");
        result.put("name", name);
        result.put("hobby", sex);

        return result;
    }

    @PostMapping("/postData")
    public Map<String, Object> postData(String name, String[] hobby){

        Map<String, Object> result = new HashMap<>();

        result.put("success", "postData成功");
        result.put("name", name);
        result.put("hobby", hobby);

        return result;
    }

    @PostMapping("/postJSON")
    public Map<String, Object> postJSON(@RequestBody() Map<String, Object> person) throws IOException {

        Map<String, Object> result = new HashMap<>();

        //JSONUtil.toBean(person.get("skill"), Skill.class);

        //JSON字符串转bean
        //Skill skill = new ObjectMapper().readValue(JSONUtil.toJSONString(person.get("skill")), Skill.class);

        //logger.info("skill：{}",JSONUtil.toJSONString(skill));

        //result.put("postJSON成功:", JSONUtil.toJSONString(skill));

        return person;
    }

    @PostMapping("/postJSONByObject")
    public List<Skill> postJSONByObject(@RequestBody() List<Skill> skills) throws IOException {

        logger.info("skill：{}",JSONUtil.toJSONString(skills));

        return skills;
    }

    @PostMapping("/postFormData")
    public Map<String, String> postFormData(String name, MultipartFile[] multipartFiles, MultipartFile[] files) throws IOException, CustomException {

        Map<String, String> result = new HashMap<>();

        System.out.println(files.length);

        for(MultipartFile file: multipartFiles){
            String  fileSrc = FileUploadUtil.multipartFileUpload(file,
                    ServerConfigUtil.fileServerDir,
                    ServerConfigUtil.userAvatarDir,
                    ServerConfigUtil.userAvatarMaxSize);

            logger.info("fileSrc: {}", fileSrc);
            result.put("success", "fileSrc: " + fileSrc);
        }

        for(MultipartFile file: files){
            String  fileSrc = FileUploadUtil.multipartFileUpload(file,
                    ServerConfigUtil.fileServerDir,
                    ServerConfigUtil.userAvatarDir,
                    ServerConfigUtil.userAvatarMaxSize);

            logger.info("fileSrc: {}", fileSrc);
            result.put("success", "fileSrc: " + fileSrc);
        }

        return result;
    }

    @PostMapping("/AvatarUpload")
    public Map<String, String> AvatarUpload(){

        return null;
    }
}
