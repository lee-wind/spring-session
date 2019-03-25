package com.wind.springsession.task;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Job {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        logger.info("JobName: {}", jobExecutionContext.getJobDetail().getKey().getName());
    }
}
