package com.wind.springsession.service;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.pojo.Success;

public interface TaskService {

    Success addJob(String jobName, String jobGroup, String cronExpression,
                   String cronDescription, String jobDescription) throws CustomException;

    Success rescheduleJob(String jobName, String jobGroup, String cronExpression,
                    String cronDescription) throws CustomException;

    Success deleteJob(String jobName, String jobGroup) throws CustomException;

    Success pauseJob(String jobName, String jobGroup) throws CustomException;

    Success resumeJob(String jobName, String jobGroup) throws CustomException;
}
