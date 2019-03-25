package com.wind.springsession.controller;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @GetMapping({",", ",", "index"})
    public String task(){

        return "Hello, task";
    }

//    @GetMapping("/list")
//    public void getTaskList(){
//        taskService.getTaskList();
//    }

    @PostMapping("/add")
    public Success addTask(String jobName, String jobGroup, String cronExpression,
                           String cronDescription, String jobDescription)
            throws CustomException {

        return taskService.addJob(jobName, jobGroup, cronExpression, cronDescription,
                jobDescription);
    }

    @PostMapping("/reschedule")
    public Success rescheduleTask(String jobName, String jobGroup, String cronExpression,
                               String cronDescription) throws CustomException {

        return taskService.rescheduleJob(jobName, jobGroup, cronExpression, cronDescription);
    }

    @PostMapping("/delete")
    public Success deleteTask(String jobName, String jobGroup) throws CustomException {

        return taskService.deleteJob(jobName, jobGroup);
    }

    @PostMapping("/pause")
    public Success pauseTask(String jobName, String jobGroup) throws CustomException {

        return taskService.pauseJob(jobName, jobGroup);
    }


    @PostMapping("/resume")
    public Success resumeTask(String jobName, String jobGroup) throws CustomException {

        return taskService.resumeJob(jobName, jobGroup);
    }
}
