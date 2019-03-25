package com.wind.springsession.service.impl;

import com.wind.springsession.exception.CustomException;
import com.wind.springsession.pojo.Success;
import com.wind.springsession.service.TaskService;
import com.wind.springsession.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
//import org.slf4j.log;
//import org.slf4j.logFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    //private static final log log = logFactory.getlog(TaskService.class);

    @Resource
    private Scheduler scheduler;

    @Override
    public Success addJob(String jobName, String jobGroup, String cronExpression,
                       String cronDescription, String jobDescription) throws CustomException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

        if(checkJob(jobKey)){
            throw new CustomException("fail", "job has exist");
        }
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                .withMisfireHandlingInstructionDoNothing();
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withDescription(cronDescription).withSchedule(cronScheduleBuilder).build();

        Class<? extends Job> jobClass = getClass(jobName).getClass();

        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey)
                .withDescription(jobDescription).build();

        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);

            return new Success("success", "add success");
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }

    @Override
    public Success rescheduleJob(String jobName, String jobGroup, String cronExpression,
                              String cronDescription) throws CustomException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);


        if(!checkTrigger(triggerKey)){
            throw new CustomException("fail", "task doesn't exist");
        }

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                .withMisfireHandlingInstructionDoNothing();
        // 按新的cronExpression表达式重新构建trigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .withDescription(cronDescription).withSchedule(cronScheduleBuilder).build();

        try {
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            return new Success("success", "reschedule success");
        } catch (SchedulerException e) {
            throw new CustomException("fail", e.getMessage());
        }
    }

    @Override
    public Success deleteJob(String jobName, String jobGroup) throws CustomException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

        if(!checkJob(jobKey)){
            throw new CustomException("fail", "task doesn't exist");
        }

        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            return new Success("success", "delete success");
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }

    @Override
    public Success pauseJob(String jobName, String jobGroup) throws CustomException {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

        if(!checkJob(jobKey)){
            throw new CustomException("fail", "task doesn't exist");
        }

        try {
            scheduler.pauseJob(jobKey);
            return new Success("success", "pause success");
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }

    @Override
    public Success resumeJob(String jobName, String jobGroup) throws CustomException {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

        if(!checkJob(jobKey)){
            throw new CustomException("fail", "task doesn't exist");
        }

        try {
            scheduler.resumeJob(jobKey);
            return new Success("success", "resume success");
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }

    }


    private boolean checkJob(JobKey jobKey) throws CustomException {
        try{
            return scheduler.checkExists(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }

    private boolean checkTrigger(TriggerKey triggerKey) throws CustomException {
        try{
            return scheduler.checkExists(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }

    private static Job getClass(String classname) throws CustomException {
        try{
            Class<?> clazz = Class.forName(classname);
            return (Job)clazz.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new CustomException("fail", e.getMessage());
        }
    }
}
